package com.qa.salesforce.test;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.salesforce.pages.BasePage;
import com.qa.salesforce.pages.UserMenuPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UserMenuTests extends BaseTest {

//SoftAssert softAssert;

	@BeforeMethod
	public void loginToSalesforceAppTest() {
		String actualTitle = loginPage.doGetLoggedIn(prop.getProperty("username"), prop.getProperty("password"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("logged in to the app");
	}

	@Test(enabled = true)
	public void testCase5() {
		Assert.assertTrue(userMenuPage.isUserMenuWebEleDisplayed());
		System.out.println("user menu is displayed");
		String[] expectedMenulist = { "My Profile", "My Settings", "Developer Console",
				"Switch to Lightning Experience", "Logout" };
		List<String> actualMenuList = userMenuPage.getUserMenuListItems();
		Assert.assertEquals(actualMenuList, Arrays.asList(expectedMenulist));
		System.out.println("usermenu is verified successfully: testcase 5 is passed");
	}

	@Test  (enabled = false)
	public void testCase6() throws InterruptedException {
		Assert.assertTrue(userMenuPage.doClickOnMyProfile(), "user profile page got displayed successfully");

		String lastName = "karna";
		String fullName = userMenuPage.doClickOnEditProfile(lastName);
		Assert.assertTrue(fullName.contains(lastName), "lastname updated successfully");

		String expectedtextPosted = "hi this is Divya";
		String actualTextPosted = userMenuPage.doClickOnPostTab(expectedtextPosted);
		Assert.assertTrue(expectedtextPosted.contains(actualTextPosted), "text posted successfully");

		String FilePath = "C:\\Users\\Rajesh\\Desktop\\Assignments\\Assignment 1\\Question 1.jpg";
		String expectedFileName = "Question 1";

		String actualFileName = userMenuPage.doClickOnFileTab(FilePath);
		Assert.assertTrue(expectedFileName.contains(actualFileName), "file got uploaded successfully");

		String photoFilePath = "C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert.jpg";

		Assert.assertTrue(userMenuPage.uploadPhoto(photoFilePath), "photo uploaded successfully");

	}
	@Test
	public void testCase7() throws InterruptedException {
		String actualTitle=userMenuPage.doClickOnPersonalLink();
		String expectedTitle="Login History ~ Salesforce - Developer Edition";
		Assert.assertTrue(expectedTitle.contains(actualTitle));
		Assert.assertTrue(userMenuPage.doClickOnDisplayAndLayoutLink());
		String actualText=userMenuPage.doClickOnEmailLink();
		String expectedText="Your settings have been successfully saved.";
		Assert.assertTrue(expectedText.contains(actualText));
		String actualPopUpTitle=userMenuPage.doClickOnCalendarsAndRemainders();
		String expectedPopUpTitle="2 Reminders";
		Assert.assertTrue(expectedPopUpTitle.contains(actualPopUpTitle));
	}
	@Test
	public void testCase8() {
		String actualDeveloperConsoleTitle=userMenuPage.doClickOnDeveloperConsole();
		String expectedDeveloperConsoleTitle="Developer Console";
		Assert.assertTrue(expectedDeveloperConsoleTitle.contains(actualDeveloperConsoleTitle));
		
		String actualHomePageTitle=userMenuPage.getTitleAfterClosingDeveloperConsoleWindow();
		String expeectedHomePageTitle="Salesforce - Developer Edition";
		Assert.assertTrue(expeectedHomePageTitle.contains(actualHomePageTitle));
	}

	@AfterMethod
	public void doLogout() {
		String actualTitle = loginPage.doClickOnlogOut();
		String expectedTitle = "Login | Salesforce";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("logged out of the app");
	}

}