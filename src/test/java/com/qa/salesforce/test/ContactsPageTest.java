package com.qa.salesforce.test;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ContactsPageTest extends BaseTest{
	@BeforeMethod
	public void loginToSalesforceAppTest() {
		String actualTitle = loginPage.doGetLoggedIn(prop.getProperty("username"), prop.getProperty("password"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("logged in to the app");
	}
	//@Test
	public void testCase25() {
		String expectedTitle="Contacts: Home ~ Salesforce - Developer Edition";
		String actualTitle=contactsPage.doClickOnContacts();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		String actualContactsNewEditTitle="Contact Edit: New Contact ~ Salesforce - Developer Edition";
		String expectedContactsNewEditTitle=contactsPage.doClickOnNewButton();
		Assert.assertEquals(actualContactsNewEditTitle, expectedContactsNewEditTitle);
		
		String generateRandomLastName=RandomStringUtils.randomAlphabetic(4);
		String actualLastNameEntered=contactsPage.enterLastName(generateRandomLastName);
		String expectedLastNameEntered=generateRandomLastName;
		Assert.assertEquals(expectedLastNameEntered, actualLastNameEntered);
		
//		String AccountName="divya";
//		String actualAccountNameEntered=contactsPage.enterAccountName(AccountName);
//	
//		Assert.assertTrue( actualAccountNameEntered.contains(AccountName));
//		
		String actualNewContactPageTitle=contactsPage.doClickOnSave(generateRandomLastName);
		Assert.assertTrue(actualNewContactPageTitle.contains(generateRandomLastName));
	}
	//@Test
	public void testCase26() {
		String expectedTitle="Contacts: Create New View ~ Salesforce - Developer Edition";
		String actualTitle=contactsPage.doClickOnCreateNewView();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		String generateViewName=RandomStringUtils.randomAlphabetic(4);
		String enterdviewNameValue=contactsPage.enterViewName(generateViewName);
		Assert.assertEquals(enterdviewNameValue, generateViewName);
		
		String enterdUniqueViewName=contactsPage.enterUniqueViewName();
		Assert.assertEquals(enterdUniqueViewName, generateViewName);
		
		String selectedOption=contactsPage.doClickOnContactsSave();
		Assert.assertEquals(selectedOption, generateViewName);
	}
	
	//@Test
	public void testCase27() {
		String expectedOption="Recently Created";
		String selectedOption=contactsPage.doSelectRecentlyCreated();
		Assert.assertEquals(expectedOption, selectedOption);
		
	}
	//@Test
	public void testCase28() {
		String expectedOption="My Contacts";
		String selectedOption=contactsPage.doSelectMyContacts();
		Assert.assertEquals(expectedOption, selectedOption);
	}
	//@Test
	public void testCase29() {
		Assert.assertTrue(contactsPage.doClickOnAContactName(2));
	}
	//@Test
	public void testCase30() {
		String expectedTitle="Contacts: Create New View ~ Salesforce - Developer Edition";
		String actualTitle=		contactsPage.doClickOnCreateNewView();
		Assert.assertEquals(expectedTitle, actualTitle);
		
		String generateViewUniqueName=RandomStringUtils.randomAlphabetic(4);
		String actualEntereduniqueName=contactsPage.enterOnlyUniqueViewName(generateViewUniqueName);
		Assert.assertEquals(generateViewUniqueName, actualEntereduniqueName);
		
		String expectedErrorMessage="Error: You must enter a value";
		String actualErrorMessage=contactsPage.doClickOnSaveWithoutEnteringViewName();
		Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
	}
	//@Test
	public void testCase31() {
		String expectedTitle="Contacts: Home ~ Salesforce - Developer Edition";
		String actualTitle=contactsPage.doClickOnContacts();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		String expectedNewViewTitle="Contacts: Create New View ~ Salesforce - Developer Edition";
		String actualNewViewTitle=contactsPage.doClickOnCreateNewView();
		Assert.assertEquals(actualNewViewTitle, expectedNewViewTitle);
		
		String viewNameValue="ABCD";
		String viewUniqueNameValue="EFGH";
		Assert.assertTrue(contactsPage.doEnterValues(viewNameValue, viewUniqueNameValue));
		
		String expectedTitleAfterCancel="Contacts: Home ~ Salesforce - Developer Edition";
		String actualTitleAfterCancel=contactsPage.doClickOnCancel();
		Assert.assertEquals(expectedTitleAfterCancel, actualTitleAfterCancel);
		
	}
	@Test
	public void testCase32() {
		String expectedTitle="Contacts: Home ~ Salesforce - Developer Edition";
		String actualTitle=contactsPage.doClickOnContacts();
		Assert.assertEquals(actualTitle, expectedTitle);
		
		String actualContactsNewEditTitle="Contact Edit: New Contact ~ Salesforce - Developer Edition";
		String expectedContactsNewEditTitle=contactsPage.doClickOnNewButton();
		Assert.assertEquals(actualContactsNewEditTitle, expectedContactsNewEditTitle);
		
		String lastNameValue="Indian";
		String accountNameValue="divya5";//Global Media
		Assert.assertTrue(contactsPage.doEnterLastNameAndAccountName(lastNameValue, accountNameValue));
		
		String expectedPageTitleAfterSaveAndNew="Contact Edit: New Contact ~ Salesforce - Developer Edition";
		String actualPageTitleAfterSaveAndNew=contactsPage.doClickOnSaveAndNew();
		Assert.assertEquals(expectedPageTitleAfterSaveAndNew, actualPageTitleAfterSaveAndNew);
		//checking whether created contact got created or not
		Assert.assertTrue(contactsPage.doCheckAccountGotCreated(lastNameValue));

	}
	
	

}
