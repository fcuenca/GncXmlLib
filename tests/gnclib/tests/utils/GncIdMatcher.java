package gnclib.tests.utils;

import java.util.UUID;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public final class GncIdMatcher
{

	private static abstract class MatcherBase<ElementType> extends TypeSafeMatcher<ElementType>
	{
		private String _msg;

		@Override
		public void describeTo(Description description)
		{
			description.appendText("not a valid Gnc ID: " + _msg);
		}

		protected boolean valueIsValidUUIDstring(String value)
		{
			String hexStringWithInsertedHyphens = value.replaceFirst(
					"(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)",
					"$1-$2-$3-$4-$5");

			try
			{
				UUID.fromString(hexStringWithInsertedHyphens);
				return true;
			}
			catch (IllegalArgumentException e)
			{
				_msg = "is not a valid UUID: " + value;
				return false;
			}
		}

		protected boolean dashesHaveBeenRemoved(String value)
		{
			if (value != null && value.indexOf('-') == -1)
			{
				return true;
			}
			else
			{
				_msg = "dashes haven't been removed in " + value;
				return false;
			}
		}

		protected boolean typeIsSetToGuid(String type)
		{
			if (type != null && type.equals("guid"))
			{
				return true;
			}
			else
			{
				_msg = "type not set or not set to guid";
				return false;
			}
		}

	}

	public static Matcher<org.gnucash.xml.trn.Id> validTrnId()
	{
		return new MatcherBase<org.gnucash.xml.trn.Id>()
		{
			@Override
			public boolean matchesSafely(org.gnucash.xml.trn.Id item)
			{
				return typeIsSetToGuid(item.getType()) && dashesHaveBeenRemoved(item.getValue())
						&& valueIsValidUUIDstring(item.getValue());
			}
		};
	}

	public static Matcher<org.gnucash.xml.split.Id> validSplitId()
	{
		return new MatcherBase<org.gnucash.xml.split.Id>()
		{
			@Override
			public boolean matchesSafely(org.gnucash.xml.split.Id item)
			{
				return typeIsSetToGuid(item.getType()) && dashesHaveBeenRemoved(item.getValue())
						&& valueIsValidUUIDstring(item.getValue());
			}
		};
	}

	public static Matcher<org.gnucash.xml.act.Id> validAccId()
	{
		return new MatcherBase<org.gnucash.xml.act.Id>()
		{
			@Override
			public boolean matchesSafely(org.gnucash.xml.act.Id item)
			{
				return typeIsSetToGuid(item.getType()) && dashesHaveBeenRemoved(item.getValue())
						&& valueIsValidUUIDstring(item.getValue());
			}
		};
	}
	
	public static Matcher<org.gnucash.xml.act.Parent> validAccParentId()
	{
		return new MatcherBase<org.gnucash.xml.act.Parent>()
		{
			@Override
			public boolean matchesSafely(org.gnucash.xml.act.Parent item)
			{
				return typeIsSetToGuid(item.getType()) && dashesHaveBeenRemoved(item.getValue())
						&& valueIsValidUUIDstring(item.getValue());
			}
		};
	}

}