package gnclib.tests.unit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import gnclib.GncFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.zip.GZIPInputStream;

import org.gnucash.xml.gnc.Transaction;
import org.junit.Test;

public class FileSaveTests
{

	@Test
	public void file_can_be_saved_to_stream() throws IOException
	{
		GncFile original = new GncFile(getClass().getResource("checkbook.xml").getPath());

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		original.saveTo(stream);

		GncFile saved = new GncFile(new ByteArrayInputStream(stream.toByteArray()));

		assertThat(saved.getTransactionCount(), is(original.getTransactionCount()));
	}

	@Test
	public void on_save_proper_xml_namespaces_are_used() throws IOException
	{
		GncFile original = new GncFile(getClass().getResource("checkbook.xml").getPath());

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		original.saveTo(stream);

		String content = stream.toString();

		assertThat(content, not(containsString("xmlns:ns")));

		assertThat(content, containsString("xmlns:gnc"));
		assertThat(content, containsString("xmlns:book"));
		assertThat(content, containsString("xmlns:trn"));
		assertThat(content, containsString("xmlns:act"));
	}

	@Test
	public void output_is_compressed_if_input_was_compressed() throws IOException
	{
		GncFile original = new GncFile(getClass().getResource("checkbook.xml.gz").getPath());

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		original.saveTo(stream);

		assertThat(isGzipStream(stream.toByteArray()), is(true));
	}

	public static boolean isGzipStream(byte[] bytes)
	{
		int head = ((int) bytes[0] & 0xff) | ((bytes[1] << 8) & 0xff00);
		return (GZIPInputStream.GZIP_MAGIC == head);
	}

	@Test
	public void newly_added_transactions_are_saved() throws IOException
	{
		GncFile original = new GncFile(getClass().getResource("checkbook.xml").getPath());

		Transaction tx = original.addTransaction(new Date(), "new tx", new BigDecimal("1.0"), "acc id", "acc id");

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		original.saveTo(stream);

		GncFile saved = new GncFile(new ByteArrayInputStream(stream.toByteArray()));

		assertThat(saved.getTransactionCount(), is(original.getTransactionCount()));
		assertThat(saved.getTransactionById(tx.getId()), is(notNullValue()));
	}
}
