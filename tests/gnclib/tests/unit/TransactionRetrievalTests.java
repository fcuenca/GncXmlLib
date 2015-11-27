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
	private GncFile _gnc;

	@Before
	public void Setup() throws IOException
	{
		_gnc = new GncFile(TestFiles.GNC_TEST_FILE);
	}


	@Test
	public void can_find_transactions_for_account()
	{
		List<Transaction> txList = _gnc.findTransactionsForTargetAccount("Expenses");
		
		assertThat(txList.size(), is(1));
	}
		
	@Test
	public void returns_empty_list_if_no_transactions_are_found()
	{
		List<Transaction> txList = _gnc.findTransactionsForTargetAccount("Assets");
		
		assertThat(txList.size(), is(0));
	}



	@Test(expected=IllegalArgumentException.class)
	public void throws_exception_if_account_doesnt_exist()
	{
		_gnc.findTransactionsForTargetAccount("this will throw");
	}	
}
