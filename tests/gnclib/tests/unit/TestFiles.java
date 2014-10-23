package gnclib.tests.unit;


public class TestFiles
{
	public static final String GNC_TEST_FILE = getFilePath("checkbook.xml");
	public static final String GNC_TEST_FILE_COMPRESSED = getFilePath("checkbook.xml.gz");

	private static String getFilePath(String fileName)
	{
		return TestFiles.class.getResource(fileName).getPath();
	}


}
