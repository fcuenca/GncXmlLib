package gnclib.tests.unit;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import gnclib.GncFile;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

import org.gnucash.xml.gnc.Transaction;
import org.gnucash.xml.trn.Id;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;

public class TransactionCreationTests
{
	private Transaction _tx;

	@Before
	public void Setup() throws IOException
	{
		GncFile gnc = new GncFile(getClass().getResource("checkbook.xml").getPath());

		@SuppressWarnings("deprecation")
		Date date = new Date(2014 - 1900, 4, 10, 15, 35, 7);

		_tx = gnc.addTransaction(date, "Coffee w/John", -9.95,
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

		// assertThat(_tx.getSplits().getSplit().get(0), is())
	}

	private Matcher<Id> validTrnId()
	{
		return new TypeSafeMatcher<Id>()
		{
			private String _msg;

			@Override
			public void describeTo(Description description)
			{
				description.appendText("not a valid Gnc ID: " + _msg);
			}

			@Override
			public boolean matchesSafely(Id item)
			{
				return typeIsSetToGuid(item) && dashesHaveBeenRemoved(item) && valueIsValidUUIDstring(item);
			}

			private boolean valueIsValidUUIDstring(Id item)
			{
				String hexStringWithInsertedHyphens = item.getValue().replaceFirst(
						"(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
						"$1-$2-$3-$4-$5");

				try
				{
					UUID.fromString(hexStringWithInsertedHyphens);
					return true;
				}
				catch (IllegalArgumentException e)
				{
					_msg = "is not a valid UUID: " + item.getValue();
					return false;
				}
			}

			private boolean dashesHaveBeenRemoved(Id item)
			{
				if (item.getValue() != null && item.getValue().indexOf('-') == -1)
				{
					return true;
				}
				else
				{
					_msg = "dashes haven't been removed in " + item.getValue();
					return false;
				}
			}

			private boolean typeIsSetToGuid(Id item)
			{
				if (item.getType() != null && item.getType().equals("guid"))
				{
					return true;
				}
				else
				{
					_msg = "type not set or not set to guid";
					return false;
				}
			}

		};
	}

	private Matcher<String> matchesPattern(final String regex)
	{
		return new TypeSafeMatcher<String>()
		{

			private Pattern _pattern = Pattern.compile(regex);

			@Override
			public void describeTo(Description description)
			{
				description.appendText("a string with pattern \"").appendText(
						String.valueOf(_pattern)).appendText("\"");

			}

			@Override
			public boolean matchesSafely(String item)
			{
				return _pattern.matcher(item).matches();
			}
		};

	}
}
