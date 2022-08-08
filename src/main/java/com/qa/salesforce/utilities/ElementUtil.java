package com.qa.salesforce.utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	private WebDriver driver;

	public ElementUtil(WebDriver driver) {

		this.driver = driver;
	}

	public WebElement getWebElement(String locatorName, String value) {
		WebElement element = null;
		switch (locatorName.toLowerCase()) {
		
		case "name":
			driver.findElement(By.name(value));

			break;
		case "classname":
			element = driver.findElement(By.className(value));
			break;
		case "id":
			element = driver.findElement(By.id(value));
			break;
		case "tagname":
			element = driver.findElement(By.tagName(value));
			break;
		case "linktext":
			element = driver.findElement(By.linkText(value));
			break;
		case "partiallinktext":
			element = driver.findElement(By.partialLinkText(value));
			break;
		case "xpath":
			element = driver.findElement(By.xpath(value));
			break;
		case "cssselector":
			element = driver.findElement(By.cssSelector(value));
			break;

		default:
			System.out.println("enter proper locator type");
			break;
		}
		return element;
	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public void doClick(By locator) {
		getElement(locator).click();

	}

	public void doSendKeys(By locator, String text) {
		getElement(locator).sendKeys(text);
		;

	}

	public String doGetElementText(By locator) {
		return getElement(locator).getText();

	}

	public String doGetElementAttribute(By locator, String atrName) {
		return getElement(locator).getAttribute(atrName);

	}

	public boolean getIsDisplayed(By locator) {
		return getElement(locator).isDisplayed();
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	public int getElementCount(By locator) {
		return getElements(locator).size();
	}

	public void clickOnElementFromSection(By locator, String value) {
		List<WebElement> webElementList = getElements(locator);
		for (WebElement e : webElementList) {
			String text = e.getText();
			if (text.equals(value)) {
				e.click();
				break;
			}
		}
	}

	public void printAllElementsList(By locator) {
		List<WebElement> webElementList = getElements(locator);
		for (WebElement e : webElementList) {
			String text = e.getText();
			System.out.println(text);
		}
	}

	public List<String> getElementsStringList(By locator) {
		List<WebElement> webElementList = getElements(locator);
		List<String> stringList = new ArrayList<String>();
		for (WebElement e : webElementList) {
			String text = e.getText();
			stringList.add(text);
		}
		return stringList;
	}

//###################### Select class utils ##############################

	public void doSelectDropDownByIndex(By locator, int index) {
		Select select = new Select(getElement(locator));
		select.selectByIndex(index);
	}

	public void doSelectDropDownByValue(By locator, String value) {
		Select select = new Select(getElement(locator));
		select.selectByValue(value);
	}

	public void doSelectDropDownByVisibleText(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public int getSelectDropDownOptionsCount(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		return optionsList.size();
	}

	public void printSelectDropDownValues(By locator) {
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			System.out.println(e.getText());
		}
	}

	public List<String> getSelectDropDownValuesList(By locator) {
		List<String> valuesList = new ArrayList<String>();
		Select select = new Select(getElement(locator));
		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			valuesList.add(text);
		}
		return valuesList;
	}

//########################## Wait class utils #######################

	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return ele;
	}

	public String waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleContains(titleFraction))) {
			return driver.getTitle();
		}
		return null;

	}

	public String waitForTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		if (wait.until(ExpectedConditions.titleIs(title))) {
			return driver.getTitle();
		}
		return null;
	}

}
