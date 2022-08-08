package com.qa.salesforce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.salesforce.utilities.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {
	WebDriver driver;
	ElementUtil eleUtil;
	By userName = By.id("username");
	By passWord = By.id("password");
	By loginbtn = By.id("Login");
	By rememberMe=By.id("rememberUn");
	By userMenu=By.id("userNavLabel");
	By logout=By.xpath("//a[text()='Logout']");
	By forgotPassword=By.id("forgot_password_link");
	
	@BeforeMethod
	public void setup() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/");

		eleUtil = new ElementUtil(driver);

	}

	@Test(enabled=true)
	public void testCase2() {

		eleUtil.doSendKeys(userName, "lelladivya26@gmail.com");
		eleUtil.doSendKeys(passWord, "test@123");
		eleUtil.doClick(loginbtn);
		String actualTitle=eleUtil.waitForTitleContains("Salesforce", 10);
		String ExpectedTitle="Home Page ~ Salesforce - Developer Edition";
		Assert.assertTrue(actualTitle.contains(ExpectedTitle));
	}

	@Test(enabled=true)
	public void testCase1() {
		WebElement userNameele = eleUtil.getElement(userName);
		WebElement passwordEle=eleUtil.getElement(passWord);
		WebElement loginBtnEle=eleUtil.getElement(loginbtn);
		
		String expectedUserName = "User@gmail.com";

		userNameele.sendKeys("User@gmail.com");
		String actualUserName = userNameele.getAttribute("value");
		System.out.println(actualUserName);
		// String actualUserName=eleUtil.doGetElementAttribute(userName,"value");
		Assert.assertEquals(actualUserName, expectedUserName);
		
		passwordEle.clear();
		String actualpassword=passwordEle.getAttribute("value");
		String expectedPassword="";
		Assert.assertEquals(actualpassword, expectedPassword);
		loginBtnEle.click();
		
		WebElement errormsg=driver.findElement(By.id("error"));
		String actualError=errormsg.getText();
		String expectedError="Please enter your password.";
		Assert.assertTrue( expectedError.contains(actualError));
	}
	@Test(enabled=true)
	public void testCase3() {
		eleUtil.doSendKeys(userName, "lelladivya26@gmail.com");
		eleUtil.doSendKeys(passWord, "test@123");
		WebElement rememberMeEle=eleUtil.getElement(rememberMe);
		rememberMeEle.click();
		eleUtil.doClick(loginbtn);
		String actualTitle=eleUtil.waitForTitleContains("Salesforce", 10);
		Assert.assertTrue(actualTitle.contains("Salesforce"));
		
		eleUtil.doClick(userMenu);
		eleUtil.doClick(logout);
		String actualtitle=eleUtil.waitForTitleContains("Login", 10);
		String ExpectedTitle="Login | Salesforce";
		Assert.assertEquals(actualtitle, ExpectedTitle);	
		String expectedUserName="lelladivya26@gmail.com";
		String actualUserName=eleUtil.doGetElementAttribute(userName, "value");
		Assert.assertEquals(actualUserName, expectedUserName);
		
	}
	@Test(enabled=true)
	public void testCase4A() {
		eleUtil.doClick(forgotPassword);
		String actualTitle=eleUtil.waitForTitleContains("Forgot", 0);
		String expectedTitle="Forgot Your Password | Salesforce";
		Assert.assertEquals(actualTitle, expectedTitle);
		By userNameFP=By.id("un");
		By continuebtn=By.id("continue");
		eleUtil.doSendKeys(userNameFP, "lelladivya26@gmail.com");
		eleUtil.doClick(continuebtn);
		String actualResetPageTitle=eleUtil.waitForTitleContains("Email", 10);
		String expectedResetPageTitle="Check Your Email | Salesforce";
		Assert.assertTrue(expectedResetPageTitle.contains(actualResetPageTitle));
	}
	@Test
	public void testCase4B() {
		eleUtil.doSendKeys(userName, "123");
		eleUtil.doSendKeys(passWord, "22131");
		eleUtil.doClick(loginbtn);
		By errorMsg=By.id("error");
		
		WebElement errorMsgEle=eleUtil.waitForElementVisible(errorMsg, 10);
		String actualErrorMsg=errorMsgEle.getText();
		String expectedErrorMsg="Please check your username and password. If you still can't log in, contact your Salesforce administrator.";
		Assert.assertEquals(actualErrorMsg, expectedErrorMsg);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
}
