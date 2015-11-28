package gnclib.tests.unit;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.is;
import gnclib.GncFile;

import java.io.IOException;
import java.util.List;

import org.gnucash.xml.gnc.Transaction;
import org.junit.Before;
import org.junit.Test;

public class TransactionRetrievalTests
{
	private static final String TARGET_ACCOUNT_ID = "e31486ad3b2c6cdedccf135d13538b29";
	private static final String OTHER_ACCOUNT_ID = "5b34e5c992f4f0279c17ed2a19bc6d7f";

	private GncFile _gnc;

	@Before
	public void Setup() throws IOException
	{
		_gnc = new GncFile(TestFiles.GNC_TEST_FILE);
	}


	@Test
	public void can_find_transactions_for_account()
	{
		List<Transaction> txList = _gnc.findTransactionsForTargetAccount(TARGET_ACCOUNT_ID);
		
		assertThat(txList.size(), is(1));
	}
		
	@Test
	public void returns_empty_list_if_no_transactions_are_found()
	{
		List<Transaction> txList = _gnc.findTransactionsForTargetAccount(OTHER_ACCOUNT_ID);
		
		assertThat(txList.size(), is(0));
	}
}
