package com.qa.salesforce.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.qa.salesforce.utilities.ElementUtil;


public class UserMenuPage {

	WebDriver driver;
	ElementUtil eleUtil;

	public static Logger log = Logger.getLogger(UserMenuPage.class);
	By userName = By.id("username");
	By passWord = By.id("password");
	By loginbtn = By.id("Login");

	By userMenu = By.id("userNavLabel");
	By userMenuList = By.xpath("//div[@id='userNav-menuItems']/a");
	By myProfile = By.xpath("//a[text()='My Profile']");

	By editProfile = By.xpath("//a[@class='contactInfoLaunch editLink']");
	By aboutTab = By.xpath("//li[@id='aboutTab']");
	By postTab = By.xpath("//span[text()='Post']");
	By fileTab = By.cssSelector("#publisherAttachContentPost");
	By uploadFileFromComputerTab = By.cssSelector("#chatterUploadFileActionPanel");
	By chooseFile = By.cssSelector("#chatterFile");
//test case7

	By mySettings = By.xpath("//div[@id='userNav-menuItems']/a[text()='My Settings']");
	By personal = By.xpath("//div[@id='PersonalInfo']/a/span[text()='Personal']");
	By loginHistory = By.xpath("//div/a[@id='LoginHistory_font']");
	By downloadLink = By.xpath("//div[@class='pShowMore']/a");

	By displayAndLayOut = By.id("DisplayAndLayout_font");
	By customizeMyTabs = By.id("CustomizeTabs_font");

	By customAppDropDown = By.xpath("//tr/td/select[@id='p4']");
	By availableTabsDropDown = By.xpath("//td/div//select[@id='duel_select_0']");

	By addButton = By.xpath("//div//a//img[@class='rightArrowIcon']");
	By selectedTabsDropDown = By.xpath("//td//div//select[@id='duel_select_1']");
	By saveButton = By.xpath("//td//input[@name='save']");
	By topbar = By.xpath("//ul[@id='tabBar']/li");
	By pages = By.xpath("//div[@id='tsidButton']");

	By emailLink = By.id("EmailSetup_font");
	By emailSettingsLink = By.id("EmailSettings_font");
	By emailName = By.id("sender_name");
	By emailAddress = By.id("sender_email");
	By automaticBcc = By.id("auto_bcc1");
	By saveButtonEmail = By.name("save");

	By calendarRemainders = By.id("CalendarAndReminders");
	By activityRemainders = By.id("Reminders_font");
	By openATestRemainder = By.id("testbtn");

	// testcase 8
	By developerConsole = By.xpath("//div[@id='userNav-menuItems']/a[text()='Developer Console']");
	// testcase9
	By logout = By.xpath("//div[@id='userNav-menuItems']/a[text()='Logout']");
//	@FindBy(id="username")
//	public By userName;
//	
//	@FindBy(id="password")
//	public By passWord;
//	
//	@FindBy(id="Login")
//	public By loginbtn;
//	
//	@FindBy(id="userNavLabel")
//	public By userMenu;
//	
//	@FindBy(xpath="//div[@id='userNav-menuItems']/a")
//	public By userMenuList;
//	
//	@FindBy(xpath="//a[text()='My Profile']")
//	public By myProfile;
//	
//	@FindBy(xpath="//a[@class='contactInfoLaunch editLink']")
//	public By editProfile;
//	
//	@FindBy(xpath="//li[@id='aboutTab']")
//	public By aboutTab;
//	
//	@FindBy(css="#publisherAttachContentPost")
//	public By fileTab;
//	
//	@FindBy(xpath="//span[text()='Post']")
//	public By postTab;
//	
//	@FindBy(css="#chatterUploadFileActionPanel")
//	public By uploadFileFromComputerTab;
//	
//	@FindBy(css="#chatterFile")
//	public By chooseFile;

	public UserMenuPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

//testcase 5:
	public Boolean isUserMenuWebEleDisplayed() {
		WebElement userMenuEle = eleUtil.getElement(userMenu);
		return userMenuEle.isDisplayed();

	}

	public List<String> getUserMenuListItems() {

		eleUtil.doClick(userMenu);
		List<String> actualMenuList = eleUtil.getElementsStringList(userMenuList);
		eleUtil.doClick(userMenu);

		return actualMenuList;
	}

	// testCase 6:

	public boolean doClickOnMyProfile() throws InterruptedException {
		eleUtil.doClick(userMenu);

		eleUtil.doClick(myProfile);
		String title = eleUtil.waitForTitleContains("User", 10);
		System.out.println("my profile page title is : " + title);
		if (title.contains("User")) {
			System.out.println("user profile page is displayed");
			return true;
		} else {
			return false;
		}

	}

	public String doClickOnEditProfile(String lastNameValue) throws InterruptedException {

		eleUtil.waitForElementVisible(editProfile, 10).click();
		// Thread.sleep(4000);

		// for iframe
		driver.switchTo().frame(eleUtil.waitForElementVisible(By.id("contactInfoContentId"), 10));
		System.out.println("switched to edit profile iframe");
		// driver.switchTo().frame("contactInfoContentId");
		eleUtil.doClick(aboutTab);
		System.out.println("clicked about tab");
		WebElement lastname = driver.findElement(By.id("lastName"));
		lastname.clear();
		lastname.sendKeys(lastNameValue);
		System.out.println("entered lastname value");
		driver.findElement(By.xpath("//input[@value='Save All']")).click();
		driver.switchTo().defaultContent();
		System.out.println("came out of the edit profile frame ");
		// Thread.sleep(2000);
		String name = driver.findElement(By.xpath("//span[@id='tailBreadcrumbNode']")).getText();
		return name;
	}

	public String doClickOnPostTab(String postText) throws InterruptedException {

		// for post
		eleUtil.waitForElementVisible(postTab, 10).click();
		System.out.println("clicked on post tab");
		Thread.sleep(2000);

		// switch to post frame:
		driver.switchTo().frame(eleUtil.waitForElementVisible(By.cssSelector(".cke_wysiwyg_frame "), 10));
		System.out.println("switched to post frame");
		// Thread.sleep(2000);
		WebElement post = driver.findElement(By.xpath("//body[text()='Share an update, @mention someone...']"));
		post.click();
		post.sendKeys(postText);
		System.out.println("text posted");
		driver.switchTo().defaultContent();
		System.out.println("switched out of the post frame");
		driver.findElement(By.id("publishersharebutton")).click();
		// Thread.sleep(2000);
		System.out.println("text shared");
		String actualText = driver.findElement(By.xpath("//div[@class='cxfeeditemtextadditional']/span")).getText();
		System.out.println(actualText);
		return actualText;
	}

	public String doClickOnFileTab(String FilePath) throws InterruptedException {

		// file uploading:
		eleUtil.waitForElementVisible(fileTab, 10).click();

		eleUtil.doClick(fileTab);
		System.out.println("file link clicked");
//uploading file from computer:
		eleUtil.doClick(uploadFileFromComputerTab);
		// Thread.sleep(1000);
		System.out.println("clicked on upload file from computer option");
		WebElement chooseFileEle = eleUtil.getElement(chooseFile);
//		
		chooseFileEle.sendKeys(FilePath);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("#publishersharebutton")).click();
		Thread.sleep(10000);
		System.out.println("file shared");
		String actualFileName = driver.findElement(By.xpath("(//div[@class='contentFileTitle']/a/span)[1]")).getText();
		System.out.println(actualFileName);
		return actualFileName;
	}

	public boolean uploadPhoto(String FilePath) throws InterruptedException {
		// photo update:
		boolean flag = false;
		// Thread.sleep(3000);
		WebElement moderatorEle = eleUtil.waitForElementVisible(By.id("displayBadge"), 10);
		WebElement updateEle = driver.findElement(By.xpath("(//div[@class='photoUploadSection']/a)[1]"));
		Actions act = new Actions(driver);
		act.moveToElement(moderatorEle).click(updateEle).build().perform();
		WebElement frameEle = eleUtil.waitForElementVisible(By.id("uploadPhotoContentId"), 10);
		driver.switchTo().frame(frameEle);
		driver.findElement(By.xpath("//input[@class='fileInput']")).sendKeys(FilePath);
		// Thread.sleep(3000);

		eleUtil.waitForElementVisible(By.xpath("//input[@id='j_id0:uploadFileForm:uploadBtn']"), 10).click();
		// save again
		// Thread.sleep(3000);

		eleUtil.waitForElementVisible(By.id("j_id0:j_id7:save"), 10).click();
		flag = true;
		return flag;

	}

	// testCase7:
	public String doClickOnPersonalLink() {
		eleUtil.doClick(userMenu);
		WebElement mySettingsEle = eleUtil.waitForElementVisible(mySettings, 10);
		mySettingsEle.click();
		WebElement personalEle = eleUtil.waitForElementVisible(personal, 10);
		personalEle.click();
		WebElement loginHistoryEle = eleUtil.waitForElementVisible(loginHistory, 10);
		loginHistoryEle.click();
		WebElement downloadEle = eleUtil.waitForElementVisible(downloadLink, 10);
		String LoginHistoryPageTitle = eleUtil.waitForTitleContains("Login History", 10);
		downloadEle.click();
		return LoginHistoryPageTitle;
	}
	public boolean doClickOnDisplayAndLayoutLink() {
		eleUtil.doClick(displayAndLayOut);
		eleUtil.waitForElementVisible(customizeMyTabs, 10).click();
		WebElement customAppDropDownEle=eleUtil.waitForElementVisible(customAppDropDown, 10);
		Select select=new Select(customAppDropDownEle);
		select.selectByVisibleText("Salesforce Chatter");
		
		WebElement availableTabsEle=eleUtil.getElement(availableTabsDropDown);
		Select select2=new Select(availableTabsEle);
		select2.selectByVisibleText("Reports");
		eleUtil.doClick(addButton);
		WebElement selectedTabsEle=eleUtil.getElement(selectedTabsDropDown);
		Select select3=new Select(selectedTabsEle);
		List<WebElement>selectedList=select3.getOptions();
		for(WebElement e:selectedList) {
			String text=e.getText();
			if(text.contains("Reports")) {
				System.out.println("reports are added to the selected tabs");
				break;
			}
		}
		
		eleUtil.doClick(saveButton);
		List<WebElement> topBarEle=eleUtil.getElements(topbar);
		boolean flag=false;
		for(WebElement e:topBarEle) {
			String text=e.getText();
			if(text.equals("Reports")) {
				System.out.println("reports are added to the topbar");
				flag=true;
				break;
			}
			
		}
		return flag;
		
	}
	public String doClickOnEmailLink() {
		eleUtil.doClick(emailLink);
		eleUtil.waitForElementVisible(emailSettingsLink, 10).click();
		WebElement emailNameEle=eleUtil.waitForElementVisible(emailName, 10);
		emailNameEle.clear();
		emailNameEle.sendKeys("Divya Lella");
		WebElement emailAddressEle=eleUtil.waitForElementVisible(emailAddress, 10);
		emailAddressEle.clear();
		emailAddressEle.sendKeys("lelladivya26@gmail.com");
		eleUtil.doClick(automaticBcc);
		eleUtil.doClick(saveButtonEmail);
		String text=eleUtil.waitForElementVisible(By.xpath("//td//div[text()='Your settings have been successfully saved.']"),10).getText();
		return text;
	}
	public String doClickOnCalendarsAndRemainders() throws InterruptedException {
		eleUtil.doClick(calendarRemainders);
		eleUtil.waitForElementVisible(activityRemainders, 10).click();
		eleUtil.waitForElementVisible(openATestRemainder, 10).click();
		Thread.sleep(5000);
		Set<String>windowIds=driver.getWindowHandles();
		Iterator<String> it=windowIds.iterator();
		String parentWindowId=it.next();
		String childWindowId=it.next();
		driver.switchTo().window(childWindowId);
		String childWindowTitle=driver.getTitle();
		driver.close();
		driver.switchTo().window(parentWindowId);
		return childWindowTitle;
		
	}
	//testCase8
	public String doClickOnDeveloperConsole() {
		eleUtil.doClick(userMenu);
		 eleUtil.waitForElementVisible(developerConsole, 10).click();
		 Set<String>windowIds=driver.getWindowHandles();
		 List<String>windowIdsList=new ArrayList<String>(windowIds);
		String parentWindowId= windowIdsList.get(0);
		String childWindowId= windowIdsList.get(1);
		driver.switchTo().window(childWindowId);
		String title=eleUtil.waitForTitleContains("Developer Console", 10);
		driver.close();
		driver.switchTo().window(parentWindowId);
		return title;
		
	}
	public String getTitleAfterClosingDeveloperConsoleWindow() {
		
		String title=driver.getTitle();
		return title;
	}
	//testcase9
	
	public String doClickOnLogout() {
		eleUtil.doClick(userMenu);
		eleUtil.waitForElementVisible(logout, 10).click();
		String title=eleUtil.waitForTitleContains("Login", 0);
		return title;
	}
}
