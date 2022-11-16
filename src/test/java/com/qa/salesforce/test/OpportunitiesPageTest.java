package com.qa.salesforce.test;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class OpportunitiesPageTest extends BaseTest{
	@BeforeMethod
	public void loginToSalesforceAppTest() throws InterruptedException {
		String actualTitle = loginPage.doGetLoggedIn(prop.getProperty("username"), prop.getProperty("password"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("logged in to the app");
	}
	@Test
	public void testCase15() {
		String actualTitle=opportunitiesPage.doClickOnOpportunities();
		String expectedTitle="Opportunities: Home ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		
		String[] expectedDropDownElements=
			{"All Opportunities","Closing Next Month","Closing This Month","My Opportunities","New Last Week","New This Week","Opportunity Pipeline","Private","Recently Viewed Opportunities","Won"};
				
		List<String> actualDropDownElements=opportunitiesPage.getOpportunitiesDropDownValues();
		Assert.assertEquals(actualDropDownElements,Arrays.asList( expectedDropDownElements));

		}
	@Test
	public void testCase16() {
		String opportunityName=RandomStringUtils.randomAlphabetic(3)+" Opportunity";
		String actualTitle=opportunitiesPage.createNewOpportunity(opportunityName);
		Assert.assertTrue(actualTitle.contains(opportunityName));
	}
	@Test
	public void testCase17() {
		String expectedTitle="Opportunity Pipeline ~ Salesforce - Developer Edition";
		String actualTitle=opportunitiesPage.DoClickOnOpportunitiesPipeLine();
		Assert.assertEquals(actualTitle, expectedTitle);
		
	}
	@Test
	public void testCase18() {
		String expectedTitle="Stuck Opportunities ~ Salesforce - Developer Edition";
		String actualTitle=opportunitiesPage.doClickOnStuckOpportunities();
		Assert.assertEquals(actualTitle, expectedTitle);
	}
	@Test
	public void testCase19() {
		String intervalValue ="Next FQ";
		String includeValue ="Open Opportunities";
		String actualIntervalValueReturned=opportunitiesPage.doClickOnQuaterlySummaryLink(intervalValue, includeValue);
		Assert.assertEquals(actualIntervalValueReturned, intervalValue);
	}
}
