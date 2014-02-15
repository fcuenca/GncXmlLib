package gnclib;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.gnucash.xml.gnc.Account;
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

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class GncFile
{
	private GncV2 _gnc;
	private Book _book;
	private CountData _txCount;
	private boolean _wasCompressed;

	public GncFile(String fileName) throws IOException
	{
		InputStream stream = createInputStream(fileName);

		initialize(stream);
	}

	public GncFile(InputStream stream)
	{
		initialize(stream);
	}

	private void initialize(InputStream stream)
	{
		try
		{
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
		JAXBContext context = JAXBContext.newInstance(new Class[] { org.gnucash.xml.top.ObjectFactory.class,
				org.gnucash.xml.ts.ObjectFactory.class });

		Unmarshaller unmarshaller = context.createUnmarshaller();

		return (GncV2) unmarshaller.unmarshal(stream);
	}

	private InputStream createInputStream(String fileName) throws IOException, FileNotFoundException
	{
		File inputFile = new File(fileName);

		if (isGZipped(inputFile))
		{
			_wasCompressed = true;
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
			if (t.getId().getValue().equals(id.getValue()))
			{
				return t;
			}
		}

		return null;
	}

	public Transaction addTransaction(Date date, String description, BigDecimal amount,
			String sourceAccountId, String targetAccountId)
	{
		if (date == null || amount == null || sourceAccountId == null || targetAccountId == null)
		{
			String errorMsg = "GncFile.addTransaction(" +
					date + ", " + amount + ", " + sourceAccountId + ", " + targetAccountId + ")";
			throw new IllegalArgumentException(errorMsg);
		}

		Transaction newTx = newTxElement(date, description, amount, sourceAccountId, targetAccountId);

		_book.getTransaction().add(newTx);

		incrementTxCount();

		return newTx;
	}

	private Transaction newTxElement(Date date, String description, BigDecimal amount, String sourceAccountId,
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

	private void setSplits(Transaction newTx, BigDecimal amount, String sourceAccountId, String targetAccountId)
	{
		int amountAsInt = amountInCents(amount);

		Splits txSplits = new Splits();
		txSplits.getSplit().add(newSplit(-1 * amountAsInt, targetAccountId));
		txSplits.getSplit().add(newSplit(amountAsInt, sourceAccountId));
		newTx.setSplits(txSplits);
	}

	private int amountInCents(BigDecimal amount)
	{
		try
		{
			return amount.multiply(new BigDecimal(100)).intValueExact();
		}
		catch (ArithmeticException e)
		{
			throw new RuntimeException("Amount has too many decimal digits: " + amount);
		}
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

	/*
	 * Solution to the namespace naming problem:
	 * http://hwellmann.blogspot.ca/2011
	 * /03/jaxb-marshalling-with-custom-namespace.html
	 * 
	 * TO ALLOW THE USE OF THE INTERNAL NAMESPACE PREFIX MAPPER CLASS:
	 * 
	 * - Open the Libraries tab of the Java Build Path project property window.
	 * - Expand the JRE System Library entry. - Select "Access rules" and hit
	 * the Edit button. - Click the Add button in the resulting dialog. - For
	 * the new access rule, set the resolution to Accessible and the pattern to
	 * "com/sun/xml/internal/**".
	 */
	public static class GncXmlPrefixMapper extends NamespacePrefixMapper
	{

		@Override
		public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix)
		{
			if (namespaceUri.startsWith("http://www.gnucash.org/XML/"))
				return namespaceUri.substring(
						namespaceUri.lastIndexOf('/') + 1);
			return suggestion;
		}

	}

	public void saveTo(String fileName) throws IOException
	{
		FileOutputStream stream = new FileOutputStream(fileName);
		saveTo(stream);
		stream.close();
	}

	public void saveTo(OutputStream stream) throws IOException
	{
		JAXBContext context;

		try
		{
			context = JAXBContext.newInstance(new Class[] {
					org.gnucash.xml.top.ObjectFactory.class,
					org.gnucash.xml.ts.ObjectFactory.class });

			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, new Boolean(true));
			marshaller.setProperty("com.sun.xml.internal.bind.namespacePrefixMapper", new GncXmlPrefixMapper());

			OutputStream output = stream;

			if (_wasCompressed)
			{
				output = new GZIPOutputStream(stream);
			}

			marshaller.marshal(_gnc, output);

			if (_wasCompressed)
			{
				output.close();
			}
		}
		catch (JAXBException e)
		{
			throw new RuntimeException(e);
		}
	}

	public Account findAccountByName(String accName)
	{
		for (Account acc : _book.getAccount())
		{
			if (acc.getName().equals(accName))
				return acc;
		}
		return null;
	}

}
