package gnclib.tests.unit;

import static gnclib.tests.utils.GncIdMatcher.validAccId;
import static gnclib.tests.utils.GncIdMatcher.validAccParentId;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import gnclib.GncFile;

import java.io.IOException;

import org.gnucash.xml.gnc.Account;
import org.junit.Before;
import org.junit.Test;

public class AccountCreationTests
{
	private GncFile _gnc;
	private Account _parent;
	private Account _acc;

	@Before
	public void Setup() throws IOException
	{
		_gnc = new GncFile(TestFiles.GNC_TEST_FILE);
		
		_parent = _gnc.findAccountByName("Expenses");
		_acc = _gnc.addSubAccount("New Account", "12345", _parent);
	}

	@Test
	public void basic_attributes_are_set()
	{
		assertThat(_acc.getVersion(), is("2.0.0"));
		assertThat(_acc.getId(), is(notNullValue()));
		assertThat(_acc.getId(), is(validAccId()));
		assertThat(_acc.getName(), is("New Account"));
		assertThat(_acc.getCode(), is("12345"));
	}
	
	@Test
	public void new_account_is_linked_to_its_parent()
	{		
		assertThat(_acc.getParent(), is(notNullValue()));
		assertThat(_acc.getParent(), is(validAccParentId()));
		assertThat(_acc.getParent().getValue(), is(_parent.getId().getValue()));
	}
	
	@Test
	public void parent_attributes_are_inherited()
	{
		assertThat(_acc.getType(), is(_parent.getType()));
		
		assertThat(_acc.getCommodity(), is(notNullValue()));
		assertThat(_acc.getCommodity().getId(), is(_parent.getCommodity().getId()));
		assertThat(_acc.getCommodity().getSpace(), is(_parent.getCommodity().getSpace()));
		
		assertThat(_acc.getCommodityScu(), is(_parent.getCommodityScu()));
	}
	
	@Test
	public void account_count_is_incremented_on_adding_new_account()
	{
		int initialCount = _gnc.getAccountCount();

		_gnc.addSubAccount("New Account", "12345", _parent);

		assertThat(_gnc.getAccountCount(), is(initialCount + 1));
	}

	@Test(expected = IllegalArgumentException.class)
	public void parent_cant_be_null()
	{
		_gnc.addSubAccount("New Account", "12345", null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_cant_be_null()
	{
		_gnc.addSubAccount(null, "12345", _parent);
	}

	@Test(expected = IllegalArgumentException.class)
	public void name_cant_be_blank()
	{
		_gnc.addSubAccount("  ", "12345", _parent);
	}

	@Test(expected = IllegalArgumentException.class)
	public void code_cant_be_null()
	{
		_gnc.addSubAccount("name", null, _parent);
	}

	@Test(expected = IllegalArgumentException.class)
	public void code_cant_be_blank()
	{
		_gnc.addSubAccount("name", "  ", _parent);
	}
}
