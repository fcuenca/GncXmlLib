package gnclib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.gnucash.xml.gnc.Book;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.gnc.Transaction;
import org.gnucash.xml.top.GncV2;
import org.gnucash.xml.trn.Currency;
import org.gnucash.xml.trn.DateEntered;
import org.gnucash.xml.trn.DatePosted;
import org.gnucash.xml.trn.Id;
import org.gnucash.xml.trn.Split;
import org.gnucash.xml.trn.Splits;

public class GncFile
{
	private GncV2 _gnc;
	private Book _book;
	private CountData _txCount;

	public GncFile(String fileName) throws IOException
	{
		try
		{
			InputStream stream = openXmlFile(fileName);

			_gnc = parseXml(stream);
			_book = _gnc.getBook();

		}
		catch (JAXBException e)
		{
			throw new RuntimeException(e);
		}
	}

	private GncV2 parseXml(InputStream stream) throws JAXBException
	{
		JAXBContext context;

		context = JAXBContext.newInstance(new Class[] { org.gnucash.xml.top.ObjectFactory.class,
				org.gnucash.xml.ts.ObjectFactory.class });

		Unmarshaller unmarshaller = context.createUnmarshaller();

		return (GncV2) unmarshaller.unmarshal(stream);
	}

	private InputStream openXmlFile(String fileName) throws IOException, FileNotFoundException
	{
		File inputFile = new File(fileName);

		if (isGZipped(inputFile))
		{
			return new GZIPInputStream(new FileInputStream(inputFile));
		}
		else
		{
			return new FileInputStream(inputFile);
		}
	}

	public int getTransactionCount()
	{
		return getTxCountElement().getValue();
	}

	private CountData getTxCountElement()
	{
		if (_txCount == null)
		{
			for (CountData count : _book.getCountData())
			{
				if (count.getType().equals("transaction"))
				{
					_txCount = count;
					return _txCount;
				}
			}

			throw new RuntimeException("Transaxtion CountData element not found. Malformed GNC file?");
		}

		return _txCount;
	}

	public Transaction getTransactionById(Id id)
	{
		for (Transaction t : _book.getTransaction())
		{
			if (t.getId().getValue() == id.getValue())
			{
				return t;
			}
		}

		return null;
	}

	public Transaction addTransaction(Date date, String description, double amount,
			String sourceAccountId, String targetAccountId)
	{
		Transaction newTx = newTxElement(date, description, amount, sourceAccountId, targetAccountId);

		_book.getTransaction().add(newTx);

		incrementTxCount();

		return newTx;
	}

	private Transaction newTxElement(Date date, String description, double amount, String sourceAccountId,
			String targetAccountId)
	{
		Transaction newTx = new Transaction();

		setBasicAttributes(newTx, description);
		setTxDates(newTx, date);
		setSplits(newTx, amount, sourceAccountId, targetAccountId);

		return newTx;
	}

	private void incrementTxCount()
	{
		CountData txCount = getTxCountElement();
		txCount.setValue(txCount.getValue() + 1);
	}

	private void setBasicAttributes(Transaction newTx, String description)
	{
		newTx.setVersion("2.0.0");
		newTx.setDescription(description);
		newTx.setId(newTrnId());
		newTx.setCurrency(newCADCurrency());
	}

	private void setSplits(Transaction newTx, double amount, String sourceAccountId, String targetAccountId)
	{
		int amountAsInt = (int) (amount * 100);

		Splits txSplits = new Splits();
		txSplits.getSplit().add(newSplit(-1 * amountAsInt, targetAccountId));
		txSplits.getSplit().add(newSplit(amountAsInt, sourceAccountId));
		newTx.setSplits(txSplits);
	}

	private Split newSplit(int amountAsInt, String accountId)
	{
		Split split = new Split();

		split.setReconciledState("n");

		org.gnucash.xml.split.Id splitId = new org.gnucash.xml.split.Id();
		splitId.setType("guid");
		splitId.setValue(UUID.randomUUID().toString().replace("-", ""));
		split.setId(splitId);

		split.setValue(amountAsInt + "/100");
		split.setQuantity(amountAsInt + "/100");

		org.gnucash.xml.split.Account splitAccount = new org.gnucash.xml.split.Account();
		splitAccount.setType("guid");
		splitAccount.setValue(accountId);
		split.setAccount(splitAccount);

		return split;
	}

	private void setTxDates(Transaction newTx, Date date)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z");

		DatePosted txDatePosted = new DatePosted();
		txDatePosted.setDate(dateFormat.format(date));
		newTx.setDatePosted(txDatePosted);

		DateEntered txDateEntered = new DateEntered();
		txDateEntered.setDate(dateFormat.format(date));
		newTx.setDateEntered(txDateEntered);
	}

	private Currency newCADCurrency()
	{
		Currency txCurrency = new Currency();
		txCurrency.setId("CAD");
		txCurrency.setSpace("ISO4217");
		return txCurrency;
	}

	private static boolean isGZipped(File f) throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(f, "r");
		int magic = raf.read() & 0xff | ((raf.read() << 8) & 0xff00);
		raf.close();

		return magic == GZIPInputStream.GZIP_MAGIC;
	}

	private Id newTrnId()
	{
		Id txId = new Id();
		txId.setValue(UUID.randomUUID().toString().replace("-", ""));
		txId.setType("guid");
		return txId;
	}

}
