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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.gnucash.xml.act.Commodity;
import org.gnucash.xml.act.Parent;
import org.gnucash.xml.gnc.Account;
import org.gnucash.xml.gnc.Book;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.gnc.Transaction;
import org.gnucash.xml.top.GncV2;
import org.gnucash.xml.trn.Currency;
import org.gnucash.xml.trn.DateEntered;
import org.gnucash.xml.trn.DatePosted;
import org.gnucash.xml.trn.Split;
import org.gnucash.xml.trn.Splits;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class GncFile
{
	private GncV2 _gnc;
	private Book _book;
	private CountData _txCount;
	private CountData _accCount;
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
	
	public int getAccountCount()
	{
		return getAccCountElement().getValue();
	}

	private CountData getAccCountElement()
	{
		if (_accCount == null)
		{
			_accCount = findCountDataForElement("account");
		}

		return _accCount;
	}

	private CountData findCountDataForElement(String elementName)
	{
		for (CountData count : _book.getCountData())
		{
			if (count.getType().equals(elementName))
			{
				return count;
			}
		}

		throw new RuntimeException("CountData element not found for: " + elementName + ". Malformed GNC file?");
	}

	private CountData getTxCountElement()
	{
		if (_txCount == null)
		{
			_txCount = findCountDataForElement("transaction");
		}

		return _txCount;
	}

	public Transaction getTransactionById(org.gnucash.xml.trn.Id id)
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

		Transaction newTx = newTransaction(date, description, amount, sourceAccountId, targetAccountId);

		_book.getTransaction().add(newTx);

		incrementTxCount();

		return newTx;
	}

	private Transaction newTransaction(Date date, String description, BigDecimal amount, String sourceAccountId,
			String targetAccountId)
	{
		Transaction newTx = new Transaction();

		setTxBasicAttributes(newTx, description);
		setTxDates(newTx, date);
		setTxSplits(newTx, amount, sourceAccountId, targetAccountId);

		return newTx;
	}

	private void incrementTxCount()
	{
		CountData count = getTxCountElement();
		count.setValue(count.getValue() + 1);
	}

	private void incrementAccountCount()
	{
		CountData count = getAccCountElement();
		count.setValue(count.getValue() + 1);
	}

	private void setTxBasicAttributes(Transaction newTx, String description)
	{
		newTx.setVersion("2.0.0");
		newTx.setDescription(description);
		newTx.setId(newTrnId());
		newTx.setCurrency(newCADCurrency());
	}

	private void setTxSplits(Transaction newTx, BigDecimal amount, String sourceAccountId, String targetAccountId)
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

	private org.gnucash.xml.trn.Id newTrnId()
	{
		org.gnucash.xml.trn.Id id = new org.gnucash.xml.trn.Id();
		id.setValue(UUID.randomUUID().toString().replace("-", ""));
		id.setType("guid");
		return id;
	}

	private org.gnucash.xml.act.Id newAccountId()
	{
		org.gnucash.xml.act.Id id = new org.gnucash.xml.act.Id();
		id.setValue(UUID.randomUUID().toString().replace("-", ""));
		id.setType("guid");
		return id;
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
	
	public Account findAccountById(String accId)
	{
		for (Account acc : _book.getAccount())
		{
			if (acc.getId().getValue().equals(accId))
				return acc;
		}
		return null;
	}

	public List<Account> getAccounts()
	{
		return _book.getAccount();
	}

	public Account addSubAccount(String name, String code, Account parent)
	{
		if (parent == null || name == null || 
				name.trim().isEmpty() || 
				code == null || code.trim().isEmpty())
		{
			String errorMsg = "GncFile.addSubAccount(" + name + ", " + code + ", " + parent + ")";
			throw new IllegalArgumentException(errorMsg);
		}
		
		Account newAccount = newAccount(name, code, parent);
		
		_book.getAccount().add(newAccount);

		incrementAccountCount();
		
		return newAccount;
	}

	private Account newAccount(String name, String code, Account parent)
	{
		Account acc = new Account();
		
		setAccountBasicAttr(acc, name, code);		
		setAccInheritedAttr(acc, parent);
		return acc;
	}

	private void setAccInheritedAttr(Account acc, Account parent)
	{
		acc.setParent(newAccParent(parent));
		
		acc.setType(parent.getType());

		acc.setCommodity(newCommodityFromParent(parent));
		
		acc.setCommodityScu(parent.getCommodityScu());
	}

	private Commodity newCommodityFromParent(Account parent)
	{
		Commodity c = new Commodity();
		c.setId(parent.getCommodity().getId());
		c.setSpace(parent.getCommodity().getSpace());
		return c;
	}

	private Parent newAccParent(Account parent)
	{
		Parent p = new Parent();
		p.setType("guid");
		p.setValue(parent.getId().getValue());
		return p;
	}

	private void setAccountBasicAttr(Account acc, String name, String code)
	{
		acc.setVersion("2.0.0");
		acc.setId(newAccountId());
		acc.setName(name);
		acc.setCode(code);
	}

	public List<Transaction> findTransactionsForTargetAccount(String accId)
	{
		ArrayList<Transaction> txList = new ArrayList<Transaction>();
		
		for (Transaction tx : _book.getTransaction())
		{
			if(tx.getSplits().getSplit().get(0).getAccount().getValue().equals(accId))
			{
				txList.add(tx);
			}
		}
		
		return txList;
	}

	public List<Transaction> findTransactionsMatching(String txDescRegex)
	{
		ArrayList<Transaction> result = new ArrayList<Transaction>();
		
		for (Transaction tx : _book.getTransaction())
		{
			if(tx.getDescription().matches(txDescRegex))
			{
				result.add(tx);
			}
		}
		
		return result;
	}
}
