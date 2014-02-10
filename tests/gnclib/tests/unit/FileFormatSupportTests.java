package gnclib.tests.unit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import gnclib.GncFile;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class FileFormatSupportTests
{

	@Test
	public void can_handle_uncompressed_gnc_xml_files() throws IOException
	{
		GncFile gnc = new GncFile(getClass().getResource("checkbook.xml").getPath());

		assertThat(gnc.getTransactionCount(), is(2));
	}

	@Test
	public void can_handle_compressed_gnc_xml_files() throws IOException
	{
		GncFile gnc = new GncFile(getClass().getResource("checkbook.xml.gz").getPath());

		assertThat(gnc.getTransactionCount(), is(2));
	}

	@Test(expected = FileNotFoundException.class)
	public void invalid_path_throws_exception() throws IOException
	{
		new GncFile("/does/not/exist.xml");
	}

}
