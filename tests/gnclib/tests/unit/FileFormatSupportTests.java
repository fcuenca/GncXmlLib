package gnclib.tests.unit;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import gnclib.GncFile;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

public class FileFormatSupportTests
{

	@Test
	public void can_handle_uncompressed_gnc_xml_files()
	{
		try
		{
			GncFile gnc = new GncFile(getClass().getResource("checkbook.xml").getPath());

			assertThat(gnc.getTransactionCount(), is(2));
		}
		catch (IOException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void can_handle_compressed_gnc_xml_files()
	{
		try
		{
			GncFile gnc = new GncFile(getClass().getResource("checkbook.xml.gz").getPath());

			assertThat(gnc.getTransactionCount(), is(2));

		}
		catch (IOException e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void invalid_path_throws_exception()
	{
		try
		{
			new GncFile("/does/not/exist.xml");

			fail("This should not have happened!");
		}
		catch (FileNotFoundException e)
		{
			// this is the expected behaviour
		}
		catch (IOException e)
		{
			fail(e.getMessage());
		}
	}

}
