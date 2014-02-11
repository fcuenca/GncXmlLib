package gnclib.tests.unit;

import static gnclib.tests.utils.RegexMatcher.matchesPattern;
import static gnclib.tests.utils.GncIdMatcher.validSplitId;
import static gnclib.tests.utils.GncIdMatcher.validTrnId;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import gnclib.GncFile;

import java.io.IOException;
import java.util.Date;

import org.gnucash.xml.gnc.Transaction;
import org.gnucash.xml.trn.Split;
import org.junit.Before;
import org.junit.Test;

public class TransactionCreationTests
{
	private Transaction _tx;

	@Before
	public void Setup() throws IOException
	{
		GncFile gnc = new GncFile(getClass().getResource("checkbook.xml").getPath());

		@SuppressWarnings("deprecation")
		Date date = new Date(2014 - 1900, 4, 10, 15, 35, 7);

		_tx = gnc.addTransaction(date, "Coffee w/John", -9.33,
				"64833494284bad5fb390e84d38c65a54", "e31486ad3b2c6cdedccf135d13538b29");

	}

	@Test
	public void basic_transaction_attributes_are_set()
	{
		assertThat(_tx.getVersion(), is("2.0.0"));
		assertThat(_tx.getId(), is(notNullValue()));
		assertThat(_tx.getId(), is(validTrnId()));
		assertThat(_tx.getDescription(), is("Coffee w/John"));
	}

	@Test
	public void has_curency_set_to_CAD()
	{
		assertThat(_tx.getCurrency(), is(notNullValue()));
		assertThat(_tx.getCurrency().getId(), is("CAD"));
		assertThat(_tx.getCurrency().getSpace(), is("ISO4217"));
	}

	@Test
	public void has_same_date_as_entered_and_posted()
	{
		assertThat("posted date", _tx.getDatePosted(), is(notNullValue()));
		assertThat(_tx.getDatePosted().getDate(), matchesPattern("2014-05-10 15:35:07" + " [-+][0-9]{4}"));

		assertThat("entered date", _tx.getDateEntered(), is(notNullValue()));
		assertThat(_tx.getDateEntered().getDate(), matchesPattern("2014-05-10 15:35:07" + " [-+][0-9]{4}"));
	}

	@Test
	public void accounts_are_set()
	{
		assertThat(_tx.getSplits(), is(notNullValue()));
		assertThat(_tx.getSplits().getSplit().size(), is(2));

		assertSplitEquals(_tx.getSplits().getSplit().get(0), "933/100", "e31486ad3b2c6cdedccf135d13538b29");
		assertSplitEquals(_tx.getSplits().getSplit().get(1), "-933/100", "64833494284bad5fb390e84d38c65a54");
	}

	private void assertSplitEquals(Split split, String expectedAmount, String expectedAccountId)
	{
		assertThat("split ID", split.getId(), is(validSplitId()));
		assertThat(split.getReconciledState(), is("n"));
		assertThat(split.getValue(), is(expectedAmount));
		assertThat(split.getQuantity(), is(expectedAmount));
		assertThat(split.getAccount(), is(notNullValue()));
		assertThat(split.getAccount().getType(), is("guid"));
		assertThat(split.getAccount().getValue(), is(expectedAccountId));
	}

}
