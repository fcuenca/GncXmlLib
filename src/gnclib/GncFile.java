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

		newTx.setVersion("2.0.0");
		newTx.setDescription(description);
		newTx.setId(newGncId());
		newTx.setCurrency(newCADCurrency());

		setTxDates(date, newTx);

		// int ammount = (int) (txAmount * 100);
		//
		Split target = new Split();
		// org.gnucash.xml.split.Id creditId = new org.gnucash.xml.split.Id();
		// creditId.setType("guid");
		// creditId.setValue(UUID.randomUUID().toString().replace("-", ""));
		// credit.setId(creditId);
		// credit.setReconciledState("n");
		// credit.setValue(-1 * ammount + "/100");
		// credit.setQuantity(-1 * ammount + "/100");
		// org.gnucash.xml.split.Account creditAcc = new
		// org.gnucash.xml.split.Account();
		// creditAcc.setType("guid");
		// creditAcc.setValue(accountId1); // TODO: look up acc id
		// credit.setAccount(creditAcc);
		//
		Split source = new Split();
		// org.gnucash.xml.split.Id debitId = new org.gnucash.xml.split.Id();
		// debitId.setType("guid");
		// debitId.setValue(UUID.randomUUID().toString().replace("-", ""));
		// debit.setId(debitId);
		// debit.setReconciledState("n");
		// debit.setValue(ammount + "/100");
		// debit.setQuantity(ammount + "/100");
		// org.gnucash.xml.split.Account debitAcc = new
		// org.gnucash.xml.split.Account();
		// debitAcc.setType("guid");
		// debitAcc.setValue(accountId2); // TODO:
		// // look up
		// // acc id
		// debit.setAccount(debitAcc);

		Splits txSplits = new Splits();
		txSplits.getSplit().add(source);
		txSplits.getSplit().add(target);
		newTx.setSplits(txSplits);

		return newTx;
	}

	private void setTxDates(Date date, Transaction newTx)
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
