package gnclib.tests.utils;

import java.util.regex.Pattern;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

public final class RegexMatcher extends TypeSafeMatcher<String>
{
	private Pattern _pattern;

	public static Matcher<String> matchesPattern(final String regex)
	{
		return new RegexMatcher(regex);
	}

	private RegexMatcher(String regex)
	{
		_pattern = Pattern.compile(regex);
	}

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
}