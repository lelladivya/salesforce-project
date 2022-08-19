package com.qa.salesforce.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AccountPageTests extends BaseTest {

	@BeforeMethod
	public void loginToSalesforceAppTest() throws InterruptedException {
		String actualTitle = loginPage.doGetLoggedIn(prop.getProperty("username"), prop.getProperty("password"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("logged in to the app");
		String actualTitleAccountPage = accountPage.clickOnAccounts();
		String expectedTitleAccountPage = "Accounts: Home ~ Salesforce - Developer Edition";
		Assert.assertTrue(expectedTitleAccountPage.contains(actualTitleAccountPage));
	}

	@Test
	public void testCase10() {
		String newAccountName = "Divya11";
		String newAccount = accountPage.createNewAccount(newAccountName);
		Assert.assertTrue(newAccount.contains(newAccountName));

	}

	@Test
	public void testCase11() throws InterruptedException {
		String viewName = "my view 2";
		Assert.assertTrue(accountPage.createNewView(viewName));

	}

	@Test
	public void testCase12() {
		String OptionTobeSelected = "my name10";
		String actualValue = accountPage.clickOnEditLink(OptionTobeSelected);
		Assert.assertTrue(actualValue.contains(OptionTobeSelected));
		String newViewName = "my name 8";
		Assert.assertTrue(accountPage.changeViewName(newViewName));

	}

	@Test
	public void testCase13() {
		String accountName = "Divya";
		String actualTitle = accountPage.getMergeAccounts2Page(accountName);
		Assert.assertTrue(actualTitle.contains("Step 2"));
		String actualNameDisplayed=accountPage.getAccountsMerged();
		Assert.assertTrue(actualNameDisplayed.contains(accountName));
	}
	@Test
	public void testCase14() throws InterruptedException {
		String actualTitle=accountPage.getUnSavedReport();
		String expectedTitle="Unsaved Report ~ Salesforce - Developer Edition";
		Assert.assertTrue(expectedTitle.contains(actualTitle));
		String todayDate="8/15/2022";
		Assert.assertTrue(accountPage.getSelectReportOptions(todayDate));
		String reportName="new Report";
		Assert.assertTrue(accountPage.getSaveReport(reportName));
	}
	@AfterMethod
	public void logOUtOfApp() {
		loginPage.doClickOnlogOut();
	}

}
