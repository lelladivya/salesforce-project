package com.qa.salesforce.pages;

import java.time.Duration;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.salesforce.utilities.ElementUtil;

public class AccountPage {
	LoginPage loginPage;
	WebDriver driver;
	ElementUtil eleUtil;
	Logger log = Logger.getLogger(AccountPage.class);
//	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	// tc10
	@FindBy(id = "Account_Tab")
	WebElement accountsLinkEle;

	@FindBy(xpath = "//td[@class='pbButton']/input")
	WebElement newButtonEle;

	@FindBy(id = "acc2")
	WebElement accountNameEle;

	@FindBy(xpath = "//td[@id='topButtonRow']/input[@name='save']")
	WebElement saveButtonEle;
	// tc11
	@FindBy(xpath = "//a[text()='Create New View']")
	WebElement createNewViewEle;

	@FindBy(id = "fname")
	WebElement viewNameEle;

	@FindBy(id = "devname")
	WebElement viewUniqueNameEle;

	@FindBy(name = "save")
	WebElement saveButtonNewViewEle;

	// tc12

	@FindBy(xpath = "//select[@name='fcf']")
	WebElement viewDropDownEle;

	@FindBy(xpath = "//a[text()='Edit']")
	WebElement editEle;

	@FindBy(id = "fname")
	WebElement editViewNameEle;

	@FindBy(id = "devname")
	WebElement editViewUniqueNameEle;

	@FindBy(id = "fcol1")
	WebElement fieldDropDown;

	@FindBy(id = "fop1")
	WebElement operatorDropDown;

	@FindBy(id = "fval1")
	WebElement valueTextField;

	@FindBy(id = "colselector_select_0")
	WebElement availableFieldsDropDown;

	@FindBy(className = "rightArrowIcon")
	WebElement addButtonEle;

	@FindBy(id = "colselector_select_1")
	WebElement selectedFieldsDropDown;

	@FindBy(name = "save")
	WebElement saveEditViewEle;

	// tc13
	@FindBy(xpath = "//a[text()='Merge Accounts']")
	WebElement MergeAccountsLinkEle;

	@FindBy(xpath = "//input[@id='srch']")
	WebElement textFieldInMergeAccounts;

	@FindBy(xpath = "//div[@class='pbWizardBody']/input[@value='Find Accounts']")
	WebElement FindAccountsEle;

	@FindBy(xpath = "//input[@name='goNext']")
	WebElement nextButton;

	@FindBy(xpath = "//input[@value=' Merge ']")
	WebElement mergeButton;

	// tc 14

	@FindBy(xpath = "//input[@name='dateColumn']")
	WebElement dateFieldDropDown;

	@FindBy(xpath = "//a[text()='Accounts with last activity > 30 days']")
	WebElement lastActivity30Ele;

	@FindBy(xpath = "//div[@class='x-combo-list-inner']//div[text()='Created Date']")
	WebElement createdDateEle;

	@FindBy(xpath = "//div/input[@name='startDate']//following-sibling::img")
	WebElement fromDateImgEle;

	@FindBy(xpath = "//td/em/button[text()='Today']")
	WebElement todayEle;

	@FindBy(xpath = "//div/input[@name='endDate']//following-sibling::img")
	WebElement toDateImgEle;

	@FindBy(xpath = "//td/em/button[text()='Today']")
	WebElement todayEleInToDate;

	@FindBy(xpath = "//td/em/button[text()='Save']")
	WebElement saveEleInUnsaved;

	@FindBy(xpath = "//div/input[@name='reportName']")
	WebElement reportNameEle;

	@FindBy(xpath = "//div/input[@name='reportDevName']")
	WebElement reportUniqueNameEle;

	@FindBy(xpath = "//button[text()='Save and Run Report']")
	WebElement saveAndRunButton;

	public AccountPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// tc10

	public String clickOnAccounts() throws InterruptedException {
	
		accountsLinkEle.click();
		log.info("clicked on accounts link");
		return eleUtil.waitForTitleContains("Accounts", 10);
	}

	public String createNewAccount(String name) {
		
		newButtonEle.click();
		log.info("clicked on new button");
		accountNameEle.sendKeys(name);
		saveButtonEle.click();
		log.info("created new account");
		String newAccountPageName = driver.findElement(By.xpath("//div[@class='textBlock']/h2")).getText();
		return newAccountPageName;
	}

	// tc11
	public boolean createNewView(String viewName) throws InterruptedException {
		createNewViewEle.click();
		log.info("clicked on new view");
		Thread.sleep(4000);
		viewNameEle.sendKeys(viewName);
		viewUniqueNameEle.click();
		saveButtonNewViewEle.click();
		Thread.sleep(3000);

		Select select = new Select(viewDropDownEle);
		String selectedOption = select.getFirstSelectedOption().getText();
		if (selectedOption.contains(viewName)) {
			return true;
		}
		else {
			return false;
		}

	}

	// tc12
	public String clickOnEditLink(String optionToBeSelected) {
		Select select = new Select(viewDropDownEle);
		log.info("clicked on view drop down");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(viewDropDownEle));
		select.selectByVisibleText(optionToBeSelected);
		editEle.click();
		return (editViewNameEle.getAttribute("value"));

	}

	public boolean changeViewName(String newViewName) {
		editViewNameEle.clear();
		editViewNameEle.sendKeys(newViewName);
		editViewUniqueNameEle.click();

		Select select = new Select(fieldDropDown);
		select.selectByVisibleText("Account Name");

		Select select2 = new Select(operatorDropDown);
		select2.selectByVisibleText("contains");

		valueTextField.sendKeys("a");
		Select selectAvailableFields = new Select(availableFieldsDropDown);
		selectAvailableFields.selectByVisibleText("Last Activity");

		addButtonEle.click();
		saveEditViewEle.click();
		List<WebElement> accountNameEle = driver
				.findElements(By.xpath("//tr//td//div[@class='x-grid3-cell-inner x-grid3-col-Name']"));
		List<WebElement> accountOptions = driver.findElements(By.xpath("//tr[@class='x-grid3-hd-row']//td/div/a"));
		boolean flag = false;
		for (WebElement e : accountOptions) {
			if (e.getText().contains("Last Activity")) {

				for (WebElement e2 : accountNameEle) {
					if (e.getText().contains("a")) {
						flag = true;
					}
				}
			}

		}
		return flag;

	}

	// tc 13

	public String getMergeAccounts2Page(String accountNameToBeMerged) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(MergeAccountsLinkEle));
		MergeAccountsLinkEle.click();
		wait.until(ExpectedConditions.visibilityOf(textFieldInMergeAccounts));
		textFieldInMergeAccounts.sendKeys(accountNameToBeMerged);
		FindAccountsEle.click();

		driver.findElement(By.xpath("(//th//input)[1]")).click();
		driver.findElement(By.xpath("(//th//input)[2]")).click();
		nextButton.click();

		String mergeAccountsPageHeader = driver
				.findElement(By.xpath("//div//div[@class='pbWizardTitle tertiaryPalette brandTertiaryBgr']")).getText();
		return mergeAccountsPageHeader;
	}

	public String getAccountsMerged() {
		mergeButton.click();
		Alert alert = driver.switchTo().alert();
		alert.accept();
		return driver.findElement(By.xpath("(//tr/th)[1]")).getText();

	}

	// tc 14
	public String getUnSavedReport() {
		lastActivity30Ele.click();

		return driver.getTitle();

	}

	public boolean getSelectReportOptions(String todayDate) {
		dateFieldDropDown.click();
		createdDateEle.click();

		fromDateImgEle.click();
		todayEle.click();

		toDateImgEle.click();
		todayEleInToDate.click();
		List<WebElement> accountNamesCreatedDate = driver
				.findElements(By.xpath("//td//div[@class='x-grid3-cell-inner x-grid3-col-LAST_UPDATE']"));
		boolean flag = true;
		for (WebElement e : accountNamesCreatedDate) {
			if (!e.getText().contains(todayDate)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public boolean getSaveReport(String reportName) throws InterruptedException {
		saveEleInUnsaved.click();
		Thread.sleep(5000);
		reportNameEle.sendKeys(reportName);
		reportUniqueNameEle.click();
		saveAndRunButton.click();
		Thread.sleep(5000);

		return driver.findElement(By.xpath("//div//h1[text()='" + reportName + "']")).isDisplayed();

	}
}
