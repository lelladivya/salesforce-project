package com.qa.salesforce.pages;

import org.apache.log4j.Logger;
/**
 * @author Divya
 * this page is to store all the By locators and page actions of LoginPage
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.qa.salesforce.utilities.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage {
	WebDriver driver;
	ElementUtil eleUtil;
	public static Logger log=Logger.getLogger(LoginPage.class);
	
	By userName = By.id("username");
	By passWord = By.id("password");
	By loginbtn = By.id("Login");
	By rememberMe = By.id("rememberUn");
	By userMenu = By.id("userNavLabel");
	By logout = By.xpath("//a[text()='Logout']");
	By forgotPassword = By.id("forgot_password_link");
	
//	@FindBy(id="username")
//	public By userName  ;
//	
//	@FindBy(id="password")
//	public By passWord ;
//	
//	@FindBy(id="Login")
//	public By loginbtn   ;
//	
//	@FindBy(id="rememberUn")
//	public By rememberMe ;
//	
//	@FindBy(id="userNavLabel")
//	public By userMenu  ;
//	
//	@FindBy(xpath="//a[text()='Logout']")
//	public By logout  ;
//	
//	@FindBy(id="forgot_password_link")
//	public By forgotPassword  ;
//
	/*
	 * loginPage constructor is to initialize driver of the page
	 * @param WebDriver
	 */

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);

	}
//	

	// testcase 1
	/*
	 * this method is to open the webpage based on the Url.
	 * @param String url, String expectedTitle
	 * @return this method returns boolean
	 */
	public boolean getWebPage(String url,String expectedTitle) {
		driver.get(url);
		String actualTitle=driver.getTitle();
		if(actualTitle.equals(expectedTitle)) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public String getUserNameValue(String userNameValue) {
		WebElement userNameele = eleUtil.getElement(userName);

		userNameele.sendKeys(userNameValue);
		String actualUserName = userNameele.getAttribute("value");
		//System.out.println(actualUserName);
		return actualUserName;

	}
	public String getPasswordValueWithText(String passwordValue) {
		WebElement passwordele = eleUtil.getElement(passWord);

		passwordele.sendKeys(passwordValue);
		String actualPassword = passwordele.getAttribute("value");
		//System.out.println(actualUserName);
		return actualPassword;

	}

	public String getPasswordValueWithoutText() {

		WebElement passwordEle = eleUtil.getElement(passWord);
		passwordEle.clear();
		String actualpassword = passwordEle.getAttribute("value");
		return actualpassword;
	}

	public String getErrorMsg() {
		WebElement loginBtnEle = eleUtil.getElement(loginbtn);
		loginBtnEle.click();

		WebElement errormsg = eleUtil.waitForElementVisible(By.id("error"),10);
		String actualErrorMsg = errormsg.getText();
		return actualErrorMsg;

	}
	// test case2

	public String doGetLoggedIn(String userNameValue, String passwordValue) {

		eleUtil.doSendKeys(userName, userNameValue);
		eleUtil.doSendKeys(passWord, passwordValue);
		eleUtil.doClick(loginbtn);
		String actualTitle = eleUtil.waitForTitleContains("Salesforce", 10);
		return actualTitle;
	}
	//testcase 3:
	

	public String getLoggedInWithRemember(String userNameValue, String passwordValue) {
		eleUtil.doSendKeys(userName, userNameValue);
		eleUtil.doSendKeys(passWord, passwordValue);
		WebElement rememberMeEle = eleUtil.getElement(rememberMe);
		rememberMeEle.click();
		eleUtil.doClick(loginbtn);
		String actualTitle = eleUtil.waitForTitleContains("Salesforce", 10);
		return actualTitle;
	}

	public String doClickOnlogOut() {
		eleUtil.doClick(userMenu);
		eleUtil.doClick(logout);
		String actualtitle = eleUtil.waitForTitleContains("Login", 10);
		return actualtitle;
		
	}

	public String getUserNameAttributeValue() {
		String actualUserName = eleUtil.doGetElementAttribute(userName, "value");
		return actualUserName;

	}
	// testcase 4a:

	public String getForgotPasswordTitle() {
		eleUtil.doClick(forgotPassword);
		String actualTitle = eleUtil.waitForTitleContains("Forgot", 0);
		return actualTitle;
		
	}

	public boolean verifyResetPage(String userNameValue, String expectedResetPageTitle) {

		By userNameFP = By.id("un");
		By continuebtn = By.id("continue");
		eleUtil.doSendKeys(userNameFP, userNameValue);
		eleUtil.doClick(continuebtn);
		String actualResetPageTitle = eleUtil.waitForTitleContains("Email", 10);

		if (expectedResetPageTitle.contains(actualResetPageTitle)) {
			return true;
		} else {
			return false;
		}
	}
	//logins the user:
	public void doLogin() {

		eleUtil.doSendKeys(userName, "lelladivya26@gmail.com");
		eleUtil.doSendKeys(passWord, "test@123");
		eleUtil.doClick(loginbtn);
	}


}
