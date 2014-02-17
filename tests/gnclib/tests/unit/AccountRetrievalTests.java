package gnclib.tests.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import gnclib.GncFile;

import java.io.IOException;
import java.util.List;

import org.gnucash.xml.gnc.Account;
import org.junit.Before;
import org.junit.Test;

public class AccountRetrievalTests
{
	private GncFile _gnc;

	@Before
	public void Setup() throws IOException
	{
		_gnc = new GncFile(getClass().getResource("checkbook.xml").getPath());
	}

	@Test
	public void can_find_account_by_unique_name()
	{
		Account acc = _gnc.findAccountByName("Expenses");

		assertThat(acc, is(notNullValue()));
		assertThat(acc.getName(), is("Expenses"));
		assertThat(acc.getId().getValue(), is("e31486ad3b2c6cdedccf135d13538b29"));
	}

	@Test
	public void returns_null_if_name_cant_be_found()
	{
		assertNull(_gnc.findAccountByName("doesn't exist"));
	}

	@Test
	public void can_retrieve_complete_account_list()
	{
		List<Account> accounts = _gnc.getAccounts();

		assertThat(accounts.size(), is(8));
	}
}
