package com.qa.salesforce.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.qa.salesforce.utilities.ElementUtil;

public class RandomScenariosPage {
	WebDriver driver;
	ElementUtil eleUtil;
	By homeTab = By.id("home_Tab");
	By nameLink = By.xpath("//h1/a[text()='Divya karna']");
	By userMenu = By.id("userNavLabel");
	By myProfile = By.xpath("//a[text()='My Profile']");
	// testcase34
	By editProfile = By.xpath("//a[@class='contactInfoLaunch editLink']");
	By aboutTab = By.xpath("//li[@id='aboutTab']");
	By contactTab = By.id("contactTab");
	By firstName = By.id("firstName");
	By lastNmae = By.id("lastName");
	By saveAll = By.xpath("//input[@value='Save All']");
	// testcase 35
	By plus = By.id("AllTab_Tab");
	By customizeMyTabs = By.xpath("//td/input[@value='Customize My Tabs']");
	By selectedTabs = By.id("duel_select_1");
	By remove = By.xpath("//div/a/img[@class='leftArrowIcon']");
	By availableTabs = By.id("duel_select_0");
	By saveBtn = By.xpath("//td/input[@name='save']");
	By tabBar = By.xpath("//ul[@id='tabBar']/li");
	By logout = By.xpath("//a[text()='Logout']");
	// testcase 36:
	By todayDateLink = By.xpath("//span[@class='pageDescription']/a");
	By time8PMLink = By.xpath("//div[@id='p:f:j_id25:j_id61:28:j_id64']/a");
	By subjectComboIcon = By.xpath("//div[@class='requiredInput']//a[@title='Combo (New Window)']");
	String parentWindowId;
	String childWindowId;

	By other = By.xpath("//li[@class='listItem4']/a");
	By endTime = By.id("EndDateTime_time");
	By endTimeDropDownOptions = By.xpath("//div[@class='hourPicker']/div");
	By endTime9PM = By.xpath("//div[@class='hourPicker']/div[text()='9:00 PM']");
	By saveBtnInCalendar = By.name("save");

	// testcase 37:
	By time4PMlink = By.xpath("//div[@id='p:f:j_id25:j_id61:20:j_id64']/a");
	By endTime7PM = By.xpath("//div[@class='hourPicker']/div[text()='7:00 PM']");
	By recurrenceCheckBox = By.id("IsRecurrence");
	By frequency = By.xpath("//tr/td/label[text()='Frequency']");
	By recurrenceStart = By.xpath("//tr/td/label[text()='Recurrence Start']");
	By recurrenceEnd = By.xpath("//tr/td/label[text()='Recurrence End']");
	By weeklyRadioButton=By.xpath("//div/label[text()='Weekly']//preceding-sibling::input");

	public RandomScenariosPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// testCase33
	public String doClickOnHomeTab() {
		eleUtil.waitForElementVisible(homeTab, 10).click();
		String title = eleUtil.waitForTitleContains("Salesforce - Developer Edition", 10);
		return title;
	}

	public boolean doCheckAccountNameLink(String accountName) {
		List<WebElement> links = driver.findElements(By.tagName("a"));
		boolean accountNamelink = false;
		for (WebElement e : links) {
			if (e.getText().contains(accountName)) {
				accountNamelink = true;
				break;
			}

		}
		return accountNamelink;

	}

	public String doClickOnNameLink() {
		eleUtil.waitForElementVisible(nameLink, 10).click();
		String title = eleUtil.waitForTitleContains("Divya karna", 10);
		return title;
	}

	public String doCompareWithMyProfilePage() {
		eleUtil.doClick(userMenu);

		eleUtil.waitForElementVisible(myProfile, 10);
		String title = eleUtil.waitForTitleContains("User", 10);
		return title;
	}

	// testCase34
	public boolean doClickOnEditProfile() {
		eleUtil.waitForElementVisible(editProfile, 10).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		// WebElement
		//WebElement frameEle=eleUtil.waitForElementVisible(By.id("contactInfoContentId"), 10);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.switchTo().frame(eleUtil.waitForElementVisible(By.id("contactInfoContentId"), 20));
		// System.out.println("switched to edit profile iframe");
		WebElement contactTabEle = eleUtil.waitForElementVisible(contactTab, 10);
		String classValue = contactTabEle.getAttribute("class");
		boolean flag = false;
		if (classValue.equals("zen-current")) {
			flag = true;
		}
		return flag;
	}

	public boolean doClickOnAboutTab() {
		eleUtil.doClick(aboutTab);
		WebElement firstNameEle = eleUtil.waitForElementVisible(firstName, 10);
		boolean flag = false;
		// to check focuspoint is at firstname or not;
		System.out.println(driver.switchTo().activeElement().getText());
		
		if (driver.switchTo().activeElement().equals(firstNameEle)) {
			flag = true;
		}
		return flag;
	}

	public boolean doEditLastName(String lastNameValue) {
		WebElement lastnameEle = eleUtil.waitForElementVisible(lastNmae, 10);
		lastnameEle.clear();
		lastnameEle.sendKeys(lastNameValue);
		eleUtil.doClick(saveAll);
		driver.switchTo().defaultContent();
		// checking the name displayed after changing lastname:
		WebElement nameEle = eleUtil.waitForElementVisible(By.xpath("//span[@id='tailBreadcrumbNode']"), 10);
		boolean flag = false;
		if (nameEle.getText().contains(lastNameValue)) {
			flag = true;
		}
		return flag;

	}

	// testcase 35
	public String doClickOnPlusTab() {
		eleUtil.waitForElementVisible(plus, 10).click();
		String title = eleUtil.waitForTitleContains("All Tabs", 10);
		System.out.println("title of the page is : " + title);
		return title;
	}

	public String doClickOnCustomizeMyTabs() {
		eleUtil.waitForElementVisible(customizeMyTabs, 10).click();
		String title = eleUtil.waitForTitleContains("Customize My Tabs", 10);
		System.out.println("title of the page is : " + title);
		return title;
	}

	public boolean removeSelectedTabs(String option) {
		WebElement selectedTabsEle = eleUtil.waitForElementVisible(selectedTabs, 10);
		Select selectSelected = new Select(selectedTabsEle);
		selectSelected.selectByVisibleText(option);

		eleUtil.doClick(remove);
		List<WebElement> selectedOptions = selectSelected.getOptions();
		List<String> selectedOptionsList = new ArrayList<String>();
		for (WebElement e : selectedOptions) {
			String text = e.getText();
			selectedOptionsList.add(text);
		}
		boolean flag = true;
		if (selectedOptionsList.contains("option")) {
			flag = false;
			System.out.println("option is not removed");
		}
		return flag;
	}

	public boolean checkInAvailableTabs(String option) {
		WebElement availableTabsEle = eleUtil.waitForElementVisible(availableTabs, 10);
		Select selectAvailable = new Select(availableTabsEle);
		List<WebElement> availableOptions = selectAvailable.getOptions();
		List<String> availabeOptionsList = new ArrayList<String>();
		for (WebElement e : availableOptions) {
			String text = e.getText();
			availabeOptionsList.add(text);
		}
		boolean flag = false;
		if (availabeOptionsList.contains(option)) {
			flag = true;
			System.out.println("option that is removed from selected tabs is added to available tabs");
		}
		return flag;
	}

	public String doClickOnSave() {
		eleUtil.doClick(saveBtn);
		String title = eleUtil.waitForTitleContains("All Tabs", 10);
		return title;
	}

	public boolean checkTabBar(String option) {
		List<String> tabBarEleList = eleUtil.getElementsStringList(tabBar);
		boolean flag = true;
		if (tabBarEleList.contains(option)) {
			flag = false;
			System.out.println("the removed option is present at tab bar : ");
		}
		System.out.println("removed option is not there on the tab bar");
		return flag;
	}

	public String doClickOnLogout() {
		eleUtil.doClick(userMenu);
		eleUtil.waitForElementVisible(logout, 10).click();
		String title = eleUtil.waitForTitleContains("Login", 10);
		return title;
	}

	// testcase 36:
	public String getTodayDate() {

		String todayDate = eleUtil.waitForElementVisible(todayDateLink, 10).getText();
		return todayDate;
	}

	public String doClickOnTodayDate() {
		eleUtil.doClick(todayDateLink);
		String title = eleUtil.waitForTitleContains("Calendar", 10);
		return title;
	}

	public String doClickOn8PMLink() {
		eleUtil.doClick(time8PMLink);
		String title = eleUtil.waitForTitleContains("New Event", 10);
		return title;
	}

	public String doClickOnSubjectComboIcon() {
		eleUtil.waitForElementVisible(subjectComboIcon, 10).click();
		Set<String> windowIds = driver.getWindowHandles();
		List<String> windowIdsList = new ArrayList(windowIds);
		parentWindowId = windowIdsList.get(0);
		childWindowId = windowIdsList.get(1);
		driver.switchTo().window(childWindowId);
		String title = eleUtil.waitForTitleContains("ComboBox", 10);
		return title;
	}

	public String doClickOnOther() {
		eleUtil.waitForElementVisible(other, 10).click();
		driver.switchTo().window(parentWindowId);
		String title = eleUtil.waitForTitleContains("New Event", 10);
		return title;
	}

	public boolean doClickOnEndTimeField(List time) {
		eleUtil.waitForElementVisible(endTime, 10).click();
		List<String> endTimeOptionsList = eleUtil.getElementsStringList(endTimeDropDownOptions);
		boolean flag = false;
		if (endTimeOptionsList.containsAll(time)) {
			flag = true;
		}
		return flag;
	}

	public String doSelect9Pm() {
		WebElement endTimeEle = eleUtil.waitForElementVisible(endTime, 10);
		eleUtil.waitForElementVisible(endTime9PM, 10).click();
		String selectedTime = endTimeEle.getAttribute("value");
		return selectedTime;
	}

	public String doClickOnSaveInCalendarPage() {
		eleUtil.doClick(saveBtnInCalendar);
		String title = eleUtil.waitForTitleContains("Calendar", 10);
		return title;
	}

	public String getSelectedSubject() {
		String text = driver.findElement(By.xpath("//span/a/span[text()='Other']")).getText();
		return text;
	}

	// testcase 37
	public String doClickOn4PMLink() {
		eleUtil.doClick(time4PMlink);
		String title = eleUtil.waitForTitleContains("New Event", 10);
		return title;
	}

	public String doSelect7Pm() {
		WebElement endTimeEle = eleUtil.waitForElementVisible(endTime, 10);
		eleUtil.waitForElementVisible(endTime7PM, 10).click();
		String selectedTime = endTimeEle.getAttribute("value");
		return selectedTime;
	}

	public boolean doClickOnRecurrenceCheckBox() {
		WebElement recurrentCheckBoxEle = eleUtil.getElement(recurrenceCheckBox);
		eleUtil.doClick(recurrenceCheckBox);
		boolean flag = false;
		if (recurrentCheckBoxEle.isSelected()) {
			if (eleUtil.getElement(frequency).isDisplayed()) {
				if (eleUtil.getElement(recurrenceStart).isDisplayed()) {
					if (eleUtil.getElement(recurrenceEnd).isDisplayed()) {
						flag = true;
					}
				}
			}

		}
		return flag;
	}
	public boolean doSelectWeeklyRadioButton() {
		WebElement weeklyRadioButtonEle=eleUtil.getElement(weeklyRadioButton);
		weeklyRadioButtonEle.click();
		return weeklyRadioButtonEle.isSelected();
	}
	public boolean doCheckRecurseEvery() {
		String recurseEveryValue=driver.findElement(By.id("wi")).getAttribute("value");
		if(recurseEveryValue.equals("1")) {
			return true;
		}
		else return false;
	}
	public boolean currentDayOfTheWeekCheckedOrNot(String todayDay) {
		WebElement todayDayEle=driver.findElement(By.xpath("(//div/label[text()='"+todayDay+"']//preceding-sibling::input)[last()]"));
		return todayDayEle.isSelected();
	}
	public boolean doEnterEndDate(String date) {
		WebElement recurrenceEndEle=eleUtil.getElement(By.id("RecurrenceEndDateOnly"));
		recurrenceEndEle.click();
			driver.findElement(By.xpath("//div/table[@class='calDays']//tr/td[text()='"+date+"']")).click();
			
		String value=recurrenceEndEle.getAttribute("value");
		return value.contains(date);
	}
	public String doClickOnMonthView() {
		WebElement monthviewEle=eleUtil.waitForElementVisible(By.xpath("//a[@title='Month View']/img"), 10);
		//Actions act=new Actions(driver);
		//act.moveToElement(monthviewEle).click().perform();
		monthviewEle.click();
		String title=eleUtil.waitForTitleContains("Month View", 10);
		return title;
	}
	
}
