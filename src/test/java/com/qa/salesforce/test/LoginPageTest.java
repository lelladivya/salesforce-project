package com.qa.salesforce.test;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{
	
	

	@Test(enabled = true)
	public void testCase1() {
		//Assert.assertTrue(loginPage.getWebPage("https://login.salesforce.com/", "Login | Salesforce"));
		Assert.assertEquals(loginPage.getUserNameValue(prop.getProperty("username")), "lelladivya26@gmail.com");
		Assert.assertEquals(loginPage.getPasswordValueWithoutText(), "");
		String expectedErrorMesg = "Please enter your password.";
		Assert.assertEquals(loginPage.getErrorMsg(), expectedErrorMesg);
	}

	@Test(enabled = true)
	public void testCase2() {
	//	Assert.assertTrue(loginPage.getWebPage("https://login.salesforce.com/", "Login | Salesforce"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition.";
		Assert.assertEquals(loginPage.doGetLoggedIn(prop.getProperty("username"), "test@123"), expectedTitle);
	}

	@Test(enabled = true)
	public void testCase3() {
	//	Assert.assertTrue(loginPage.getWebPage("https://login.salesforce.com/", "Login | Salesforce"));
		
		String expectedTitleAfterLoggedIn = "Home Page ~ Salesforce - Developer Edition";
		String actualTitleAfterLoggedIn=loginPage.getLoggedInWithRemember(prop.getProperty("username"), "test@123");
		Assert.assertEquals(actualTitleAfterLoggedIn,expectedTitleAfterLoggedIn);
		
		String expectedTitleLogout = "Login | Salesforce";
		String actualTitleLogout=loginPage.doClickOnlogOut();
		Assert.assertEquals(actualTitleLogout, expectedTitleLogout);
		
		String actualUserNamevalue=loginPage.getUserNameAttributeValue();
		String expectedUserNameValue="lelladivya26@gmail.com";
		Assert.assertEquals(actualUserNamevalue, expectedUserNameValue);
	}

	@Test(enabled = true)
	public void testCase4A() {
	//	Assert.assertTrue(loginPage.getWebPage("https://login.salesforce.com/", "Login | Salesforce"));
		
		 String expectedTitle="Forgot Your Password | Salesforce";
		 String actualTitle=loginPage.getForgotPasswordTitle();
		 Assert.assertEquals(actualTitle, expectedTitle);
		 Assert.assertTrue(loginPage.verifyResetPage("lelladivya26@gmail.com", "Check Your Email | Salesforce"));
	}

	@Test(enabled = true)
	public void testCase4B() {
	//	Assert.assertTrue(loginPage.getWebPage("https://login.salesforce.com/", "Login | Salesforce"));
		String actualUserName=loginPage.getUserNameValue("123");
		String expectedUserName="123";
		Assert.assertEquals(actualUserName,expectedUserName );
		
		String actualPassword=loginPage.getPasswordValueWithText("22131");
		String expectedPassword="22131";
		Assert.assertEquals(actualPassword, expectedPassword);
		
		String actualErrorMsg=loginPage.getErrorMsg();
		String expectedErrorMsg="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}

	
}
