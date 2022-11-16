package com.qa.salesforce.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.salesforce.utilities.ElementUtil;

public class LeadsPage {
	WebDriver driver;
	ElementUtil eleUtil;

	By leadsTab = By.id("Lead_Tab");
	By viewDropDown = By.xpath("//span//select[@id='fcf']");
	By mainMenuDropDown = By.id("userNavLabel");
	By logout = By.xpath("//div[@id='userNav-menuItems']//a[text()='Logout']");
	By go = By.name("go");
	By leadDropDown = By.name("fcf");

	By newButton = By.name("new");
	By lastName = By.id("name_lastlea2");
	By company = By.id("lea3");
	By save = By.name("save");

	public LeadsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	// testCase20
	public String clickOnLeadsTab() {
		eleUtil.doClick(leadsTab);
		String title = eleUtil.waitForTitleContains("Leads", 10);
		return title;
	}

	// testCase21
	public List<String> leadsSelectView() {
		clickOnLeadsTab();
		WebElement viewDropDownEle = eleUtil.waitForElementVisible(viewDropDown, 10);
		Select select = new Select(viewDropDownEle);
		List<WebElement> viewOptions = select.getOptions();
		List<String> viewOptionsList = new ArrayList<String>();
		for (WebElement e : viewOptions) {
			viewOptionsList.add(e.getText());
		}
		return viewOptionsList;
	}

	// test case22
	public String doSelectViewDropDownAndLogout(String optionValue) {
		clickOnLeadsTab();
		WebElement viewDropDownEle = eleUtil.waitForElementVisible(viewDropDown, 10);
		Select select = new Select(viewDropDownEle);
		select.selectByVisibleText(optionValue);
		eleUtil.doClick(mainMenuDropDown);
		eleUtil.waitForElementVisible(logout, 10).click();
		String title = eleUtil.waitForTitleContains("Login ", 10);
		return title;
	}

	public String functionalityOfTHeGoButton() {
		eleUtil.waitForElementVisible(go, 10).click();
		WebElement leadDropDownEle = eleUtil.waitForElementVisible(leadDropDown, 10);
		Select select = new Select(leadDropDownEle);
		String selectedOption = select.getFirstSelectedOption().getText();
		return selectedOption;

	}

	// testcase 23:
	public String doSelectOptionFromLeadsDropDown(String optionValue) {
		clickOnLeadsTab();
		WebElement viewDropDownEle = eleUtil.waitForElementVisible(viewDropDown, 10);
		Select select = new Select(viewDropDownEle);
		select.selectByVisibleText(optionValue);
		eleUtil.doClick(go);
		WebElement leadDropDownEle = eleUtil.waitForElementVisible(leadDropDown, 10);
		Select select2 = new Select(leadDropDownEle);
		String selectedOption = select2.getFirstSelectedOption().getText();
		return selectedOption;
	}

	// testcase24
	public String doClickOnNewButton() {
		clickOnLeadsTab();
		eleUtil.waitForElementVisible(newButton, 10).click();
		String title = eleUtil.waitForTitleContains("New Lead", 10);
		return title;
	}

	public String doEnterLastName(String lastNameValue) {
		WebElement lastNameEle = eleUtil.waitForElementVisible(lastName, 10);
		lastNameEle.sendKeys(lastNameValue);
		String enterdValue = lastNameEle.getAttribute("value");
		return enterdValue;
	}

	public String doEnterCompanyName(String companyNameValue) {
		WebElement lastNameEle = eleUtil.waitForElementVisible(company, 10);
		lastNameEle.sendKeys(companyNameValue);
		String enterdValue = lastNameEle.getAttribute("value");
		return enterdValue;
	}

	public String doClickOnSave(String titleFraction) {
		eleUtil.doClick(save);
		String title = eleUtil.waitForTitleContains(titleFraction, 10);
		return title;
	}

}
