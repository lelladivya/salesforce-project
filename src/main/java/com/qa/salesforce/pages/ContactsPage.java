package com.qa.salesforce.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.salesforce.utilities.ElementUtil;

public class ContactsPage {
	WebDriver driver;
	ElementUtil eleUtil;
	By contacts = By.id("Contact_Tab");
	By newButton = By.name("new");
	By lastName = By.id("name_lastcon2");
	By accountName = By.id("con4");
	By save = By.name("save");
	By accountNameSelect = By.id("con4_lkwgt");
	// testcase26
	By createNewView = By.xpath("//span/a[text()='Create New View']");
	By viewName = By.id("fname");
	By viewUniqueName = By.id("devname");
	By contactsDropDown = By.name("fcf");
	// testcase27
	By recentlyCreatedDropDown = By.id("hotlist_mode");
//testCase31
	By cancel=By.name("cancel");
	By saveAndNew=By.name("save_new");
	
	
	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);

	}

	// testCase25
	public String doClickOnContacts() {
		eleUtil.waitForElementVisible(contacts, 10).click();
		String title = eleUtil.waitForTitleContains("Contacts:", 10);
		return title;
	}

	public String doClickOnNewButton() {
		eleUtil.waitForElementVisible(newButton, 10).click();
		;
		String title = eleUtil.waitForTitleContains("New Contact", 10);
		return title;
	}

	public String enterLastName(String lastNameValue) {
		WebElement lastNameEle = eleUtil.waitForElementVisible(lastName, 10);
		lastNameEle.sendKeys(lastNameValue);
		String enteredLastName = lastNameEle.getAttribute("value");
		return enteredLastName;
	}

	public String enterAccountName(String accountNameValue) {
		eleUtil.doClick(accountNameSelect);
		Set<String> windowIds = driver.getWindowHandles();
		List<String> windowIdsList = new ArrayList<String>(windowIds);
		String parentWindowId = windowIdsList.get(0);
		String childWindowId = windowIdsList.get(1);

		driver.switchTo().window(childWindowId);
		System.out.println(eleUtil.waitForTitleContains("Search ", 10));
		eleUtil.waitForElementVisible(By.xpath("(//a[text()='" + accountNameValue + "'])[1]"), 15).click();
		driver.switchTo().window(parentWindowId);
		String enteredAccountName = eleUtil.waitForElementVisible(accountName, 10).getAttribute("value");

		return enteredAccountName;
	}

	public String doClickOnSave(String lastNameValue) {
		eleUtil.doClick(save);
		return eleUtil.waitForTitleContains(lastNameValue, 10);
	}

	// testCase26:
	public String doClickOnCreateNewView() {
		doClickOnContacts();
		eleUtil.waitForElementVisible(createNewView, 10).click();
		String title = eleUtil.waitForTitleContains("Create New View", 10);
		return title;
	}

	public String enterViewName(String viewNameValue) {
		WebElement viewNameEle = eleUtil.waitForElementVisible(viewName, 10);
		viewNameEle.sendKeys(viewNameValue);
		String enteredValue = viewNameEle.getAttribute("value");
		return enteredValue;
	}

	public String enterUniqueViewName() {
		WebElement viewUniqueNameEle = eleUtil.getElement(viewUniqueName);
		viewUniqueNameEle.click();
		String enterdValue = viewUniqueNameEle.getAttribute("value");
		return enterdValue;

	}

	public String doClickOnContactsSave() {
		eleUtil.doClick(save);
		WebElement contactsDropDownEle = eleUtil.waitForElementVisible(contactsDropDown, 10);
		Select select = new Select(contactsDropDownEle);
		String text = select.getFirstSelectedOption().getText();
		return text;
	}

	// testcase27
	public String doSelectRecentlyCreated() {
		doClickOnContacts();
		WebElement recentlyCreatedDropDownEle = eleUtil.waitForElementVisible(recentlyCreatedDropDown, 10);
		Select select = new Select(recentlyCreatedDropDownEle);
		select.selectByVisibleText("Recently Created");
		String selectedOption = select.getFirstSelectedOption().getText();
		return selectedOption;
	}

	// testCasse28
	public String doSelectMyContacts() {
		doClickOnContacts();
		WebElement contactsViewDropDownEle = eleUtil.waitForElementVisible(contactsDropDown, 10);
		Select select = new Select(contactsViewDropDownEle);
		select.selectByVisibleText("My Contacts");
		String selectedOption = select.getFirstSelectedOption().getText();
		return selectedOption;
	}

	// testCase29
	public boolean doClickOnAContactName(int indexOfName) {
		doClickOnContacts();
		WebElement contactNameEle = eleUtil.waitForElementVisible(By.xpath("(//th/a)['" + indexOfName + "']"), 10);
		String selectedName = contactNameEle.getText();
		contactNameEle.click();
		String title = eleUtil.waitForTitleContains(selectedName, 10);
		if (title.contains(selectedName)) {
			return true;
		} else {
			return false;
		}

	}

	// testCase30:
	public String doClickCreateNewView() {
		doClickOnContacts();
		eleUtil.waitForElementVisible(createNewView, 10).click();
		String title = eleUtil.waitForTitleContains("Create New View", 10);
		return title;
	}

	public String enterOnlyUniqueViewName(String uniqueNameValue) {
		WebElement viewUniqueNameEle = eleUtil.waitForElementVisible(viewUniqueName, 10);
		viewUniqueNameEle.sendKeys(uniqueNameValue);
		String enterdValue = viewUniqueNameEle.getAttribute("value");
		return enterdValue;
	}

	public String doClickOnSaveWithoutEnteringViewName() {
		eleUtil.doClick(save);
		String errorText = eleUtil.waitForElementVisible(By.className("errorMsg"), 10).getText();
		return errorText;
	}

	// testCase31:
	public boolean doEnterValues(String viewNameValue, String viewUniqueNameValue) {
		WebElement viewNameEle = eleUtil.waitForElementVisible(viewName, 10);
		viewNameEle.sendKeys(viewNameValue);
		WebElement viewUniqueNameEle = eleUtil.waitForElementVisible(viewUniqueName, 10);
		viewUniqueNameEle.click();
		viewUniqueNameEle.clear();
		
		viewUniqueNameEle.sendKeys(viewUniqueNameValue);
		String enteredViewValue = viewNameEle.getAttribute("value");
		String enterdUniqueValue = viewUniqueNameEle.getAttribute("value");
		boolean flag = false;
		if (enteredViewValue.equals(viewNameValue)) {
			if (enterdUniqueValue.equals(viewUniqueNameValue)) {
				flag = true;
			}
		}
		return flag;
	}
	public String doClickOnCancel() {
		eleUtil.doClick(cancel);
		return eleUtil.waitForTitleContains("Home ", 10);
	}
	
	//testcase32
	public boolean doEnterLastNameAndAccountName(String lastNameValue,String accountNameValue) {
		WebElement lastNameEle = eleUtil.waitForElementVisible(lastName, 10);
		lastNameEle.sendKeys(lastNameValue);
		String enteredLastName = lastNameEle.getAttribute("value");
		WebElement accountNameEle=eleUtil.getElement(accountName);
		accountNameEle.sendKeys(accountNameValue);
		String enteredAccountName=accountNameEle.getAttribute("value");
		
		boolean flag=false;
		if(enteredLastName.equals(lastNameValue)) {
			if(enteredAccountName.equals(accountNameValue)) {
				flag=true;
			}
		}
		return flag;
	}
	
	public String doClickOnSaveAndNew() {
		eleUtil.doClick(saveAndNew);
		 return eleUtil.waitForTitleContains(" New Contact ", 10);
		
	}
	public boolean doCheckAccountGotCreated(String lastNameValue) {
		eleUtil.doClick(contacts);
		WebElement newContactEle=eleUtil.waitForElementVisible(By.xpath("//th/a[text()='"+lastNameValue+"']"), 10);
		return newContactEle.isDisplayed();
	}
}