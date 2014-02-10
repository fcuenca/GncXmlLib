package gnclib;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.gnucash.xml.gnc.Book;
import org.gnucash.xml.gnc.CountData;
import org.gnucash.xml.top.GncV2;

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

	private static boolean isGZipped(File f) throws IOException
	{
		RandomAccessFile raf = new RandomAccessFile(f, "r");
		int magic = raf.read() & 0xff | ((raf.read() << 8) & 0xff00);
		raf.close();

		return magic == GZIPInputStream.GZIP_MAGIC;
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

}
