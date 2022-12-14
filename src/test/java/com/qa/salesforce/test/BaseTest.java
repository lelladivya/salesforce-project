package com.qa.salesforce.test;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.qa.salesforce.pages.AccountPage;
import com.qa.salesforce.pages.BasePage;
import com.qa.salesforce.pages.ContactsPage;
import com.qa.salesforce.pages.LeadsPage;
import com.qa.salesforce.pages.LoginPage;
import com.qa.salesforce.pages.OpportunitiesPage;
import com.qa.salesforce.pages.RandomScenariosPage;
import com.qa.salesforce.pages.UserMenuPage;

public class BaseTest {
	WebDriver driver;
	LoginPage loginPage;
	BasePage bp;
	UserMenuPage userMenuPage;
	AccountPage accountPage;
	OpportunitiesPage opportunitiesPage;
	LeadsPage leadsPage;
	Properties prop;
	ContactsPage contactsPage;
RandomScenariosPage randomScenariosPage;

	@BeforeMethod
	public void setUp() {
		bp = new BasePage();
		prop = bp.init_Properties();
		driver = bp.init_Driver(prop);
		loginPage = new LoginPage(driver);
		opportunitiesPage=new OpportunitiesPage(driver);
		leadsPage=new LeadsPage(driver);
		contactsPage=new ContactsPage(driver);
		randomScenariosPage=new RandomScenariosPage(driver);
//		userMenuPage = new UserMenuPage(driver);
//	accountPage=new AccountPage(driver);
		// Assert.assertTrue(loginPage.getWebPage("https://login.salesforce.com/",
		// "Login | Salesforce"));
		 
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
