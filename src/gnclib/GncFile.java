package gnclib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

	public GncFile(String fileName) throws IOException
	{
		JAXBContext context;
		try
		{
			File inputFile = new File(fileName);

			InputStream stream;

			if (isGZipped(inputFile))
			{
				stream = new GZIPInputStream(new FileInputStream(inputFile));
			}
			else
			{
				stream = new FileInputStream(inputFile);
			}

			context = JAXBContext.newInstance(new Class[] { org.gnucash.xml.top.ObjectFactory.class,
					org.gnucash.xml.ts.ObjectFactory.class });

			Unmarshaller unmarshaller = context.createUnmarshaller();

			_gnc = (GncV2) unmarshaller.unmarshal(stream);
			_book = _gnc.getBook();

		}
		catch (JAXBException e)
		{
			throw new RuntimeException(e);
		}
	}

	public int getTransactionCount()
	{
		List<CountData> countData = _book.getCountData();

		for (CountData count : countData)
		{
			if (count.getType().equals("transaction"))
			{
				return count.getValue();
			}
		}

		throw new RuntimeException("Transaxtion CountData element not found. Malformed GNC file?");
	}

	public Transaction addTransaction(Date date, String description, double amount,
			String sourceAccountId, String targetAccountId)
	{
		Transaction newTx = new Transaction();

		setBasicAttributes(newTx, description);
		setTxDates(newTx, date);
		setSplits(newTx, amount, sourceAccountId, targetAccountId);

		return newTx;
	}

	private void setBasicAttributes(Transaction newTx, String description)
	{
		newTx.setVersion("2.0.0");
		newTx.setDescription(description);
		newTx.setId(newGncId());
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

	private Id newGncId()
	{
		Id txId = new Id();
		txId.setValue(UUID.randomUUID().toString().replace("-", ""));
		txId.setType("guid");
		return txId;
	}
}
