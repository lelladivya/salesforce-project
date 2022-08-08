package com.qa.salesforce.pages;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.salesforce.utilities.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class UserMenuPage {
	WebDriver driver;
	ElementUtil eleUtil;
	By userName = By.id("username");
	By passWord = By.id("password");
	By loginbtn = By.id("Login");
	
	By userMenu=By.id("userNavLabel");
	By userMenuList=By.xpath("//div[@id='userNav-menuItems']/a");
	@BeforeTest
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://login.salesforce.com/");
	 eleUtil=new ElementUtil(driver);
	}
	@BeforeMethod
	public void login() {
		
		eleUtil.doSendKeys(userName, "lelladivya26@gmail.com");
		eleUtil.doSendKeys(passWord, "test@123");
		eleUtil.doClick(loginbtn);
	}
	@Test
	public void testCase5() {
		WebElement userMenuEle=eleUtil.getElement(userMenu);
		Assert.assertTrue(userMenuEle.isDisplayed());
		eleUtil.doClick(userMenu);
		String[] expectedMenulist= {"My Profile", "My Settings", "Developer Console", "Switch to Lightning Experience", "Logout" };
		List<String>actualMenuList=eleUtil.getElementsStringList(userMenuList);
		Assert.assertEquals(actualMenuList, Arrays.asList(expectedMenulist));
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.close();
	}
	

}
