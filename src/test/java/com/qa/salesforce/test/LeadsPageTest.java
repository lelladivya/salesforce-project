package com.qa.salesforce.test;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LeadsPageTest extends BaseTest {
	@BeforeMethod
	public void loginToSalesforceAppTest() {
		String actualTitle = loginPage.doGetLoggedIn(prop.getProperty("username"), prop.getProperty("password"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("logged in to the app");
	}
	@Test
	public void testCase20() {
		String expectedTitle="Leads: Home ~ Salesforce - Developer Edition";
		String actualTitle=leadsPage.clickOnLeadsTab();
		Assert.assertEquals(expectedTitle, actualTitle);
	}
	@Test
	public void testCase21() {
		String[] expectedViewOptions=
			{"All Open Leads","My Unread Leads","Recently Viewed Leads","Today's Leads","View - Custom 1","View - Custom 2"};
		List<String>actualViewOptions=leadsPage.leadsSelectView();
		Assert.assertEquals(actualViewOptions, Arrays.asList(expectedViewOptions));
	}
	@Test
	public void testCase22() {
		String OptionToSelect="Today's Leads";
		String actualLoginPageTitle=leadsPage.doSelectViewDropDownAndLogout(OptionToSelect);
		String expectedPageTitle="Login | Salesforce";
		Assert.assertEquals(actualLoginPageTitle, expectedPageTitle);
		
		String actualTitle = loginPage.doGetLoggedIn(prop.getProperty("username"), prop.getProperty("password"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		
		String expectedLeadsPageTitle="Leads: Home ~ Salesforce - Developer Edition";
		String actualLeadsPageTitle=leadsPage.clickOnLeadsTab();
		Assert.assertEquals(expectedLeadsPageTitle, actualLeadsPageTitle);
		String selectedOption=leadsPage.functionalityOfTHeGoButton();
		Assert.assertEquals(OptionToSelect, selectedOption);
	}
	@Test
	public void testCase23() {
		String OptionToSelect="Today's Leads";

		String actualSelectedPage=leadsPage.doSelectOptionFromLeadsDropDown(OptionToSelect);
		Assert.assertEquals(actualSelectedPage, OptionToSelect);
	}
	@Test
	public void testCase24() {
		String expectedNewLeadsPageTitle="Lead Edit: New Lead ~ Salesforce - Developer Edition";
		String actualNewLeadsPageTitle=leadsPage.doClickOnNewButton();
		Assert.assertEquals(actualNewLeadsPageTitle, expectedNewLeadsPageTitle);
		String ValueToEnter="ABCD";
		String lastNameEnterd=leadsPage.doEnterLastName(ValueToEnter);
		Assert.assertEquals(lastNameEnterd, ValueToEnter);
		
		String companyNameEntered=leadsPage.doEnterCompanyName(ValueToEnter);
		Assert.assertEquals(ValueToEnter, companyNameEntered);
		
		String ActualNewLeadsTitle=leadsPage.doClickOnSave(ValueToEnter);
		Assert.assertTrue(ActualNewLeadsTitle.contains(ValueToEnter));
	}
}
