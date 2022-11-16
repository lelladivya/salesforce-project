package com.qa.salesforce.test;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RandomScenariosPageTest extends BaseTest {
	@BeforeMethod
	public void loginToSalesforceAppTest() {
		String actualTitle = loginPage.doGetLoggedIn(prop.getProperty("username"), prop.getProperty("password"));
		String expectedTitle = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitle, expectedTitle);
		System.out.println("logged in to the app");
	}

	// @Test
	public void testCase33() {
		String expectedHomeTitle = "Salesforce - Developer Edition";
		String actualHomeTitle = randomScenariosPage.doClickOnHomeTab();
		Assert.assertEquals(expectedHomeTitle, actualHomeTitle);
		Assert.assertTrue(randomScenariosPage.doCheckAccountNameLink("Divya karna"));

		String expectedTitle = "User: Divya karna ~ Salesforce - Developer Edition";
		String actualTitle = randomScenariosPage.doClickOnNameLink();
		Assert.assertEquals(actualTitle, expectedTitle);

		String MyProfileTitle = randomScenariosPage.doCompareWithMyProfilePage();
		String titleAfterClickingNameLink = "User: Divya karna ~ Salesforce - Developer Edition";
		Assert.assertEquals(MyProfileTitle, titleAfterClickingNameLink);
	}

	 @Test
	public void testCase34() {
		String expectedHomeTitle = "Salesforce - Developer Edition";
		String actualHomeTitle = randomScenariosPage.doClickOnHomeTab();
		Assert.assertEquals(expectedHomeTitle, actualHomeTitle);

		String expectedTitle = "User: Divya karna ~ Salesforce - Developer Edition";
		String actualTitle = randomScenariosPage.doClickOnNameLink();
		Assert.assertEquals(actualTitle, expectedTitle);
		// check contact tab is selected or not
		Assert.assertTrue(randomScenariosPage.doClickOnEditProfile());
		// check focus point
		 Assert.assertTrue(randomScenariosPage.doClickOnAboutTab());
		// checking the name contains changed lastname or not
		Assert.assertTrue(randomScenariosPage.doEditLastName("Abcd"));
	}

	// @Test
	public void testCase35() {
		String expectedAllTabsTitle = "All Tabs ~ Salesforce - Developer Edition";
		String actualAllTabsTitle = randomScenariosPage.doClickOnPlusTab();
		Assert.assertEquals(expectedAllTabsTitle, actualAllTabsTitle);

		String expectedCustomizeMyTabsTitle = "Customize My Tabs ~ Salesforce - Developer Edition";
		String actualCustomizeMyTabsTitle = randomScenariosPage.doClickOnCustomizeMyTabs();
		Assert.assertEquals(expectedCustomizeMyTabsTitle, actualCustomizeMyTabsTitle);

		String option = "Campaigns";
		Assert.assertTrue(randomScenariosPage.removeSelectedTabs(option));
		Assert.assertTrue(randomScenariosPage.checkInAvailableTabs(option));

		String actualTitleAfterSave = randomScenariosPage.doClickOnSave();
		Assert.assertEquals(actualTitleAfterSave, expectedAllTabsTitle);
		Assert.assertTrue(randomScenariosPage.checkTabBar(option));

		String expectedTitleAfterLogout = "Login | Salesforce";
		String actualTitleAfterLogout = randomScenariosPage.doClickOnLogout();
		Assert.assertEquals(expectedTitleAfterLogout, actualTitleAfterLogout);

		String actualTitleAfterLogin = loginPage.doGetLoggedIn(prop.getProperty("username"),
				prop.getProperty("password"));
		String expectedTitleAfterLogin = "Home Page ~ Salesforce - Developer Edition";
		Assert.assertEquals(actualTitleAfterLogin, expectedTitleAfterLogin);
		System.out.println("logged in to the app");
		Assert.assertTrue(randomScenariosPage.checkTabBar(option));

	}

	// @Test
	public void testCase36() {
		String expectedHomeTitle = "Salesforce - Developer Edition";
		String actualHomeTitle = randomScenariosPage.doClickOnHomeTab();
		Assert.assertEquals(expectedHomeTitle, actualHomeTitle);
		// checking date:need to change expected date every time
		String expectedtodayDate = "Monday September 5, 2022";
		String actualTodayDate = randomScenariosPage.getTodayDate();
		Assert.assertEquals(expectedtodayDate, actualTodayDate);
		// chrcking calendar page title:it may change with name
		String expectedCalendarPageTitle = "Calendar for Divya karna ~ Salesforce - Developer Edition";
		String actualCalendarPageTitle = randomScenariosPage.doClickOnTodayDate();
		Assert.assertEquals(expectedCalendarPageTitle, actualCalendarPageTitle);

		String expectedNewEventTitle = "Calendar: New Event ~ Salesforce - Developer Edition";
		String actualNewEventTitle = randomScenariosPage.doClickOn8PMLink();
		Assert.assertEquals(expectedNewEventTitle, actualNewEventTitle);

		String expectedComboBoxTitle = "ComboBox";
		String actualComboBoxTitle = randomScenariosPage.doClickOnSubjectComboIcon();
		Assert.assertEquals(expectedComboBoxTitle, actualComboBoxTitle);

		String expectedTitleAfterClickingOther = "Calendar: New Event ~ Salesforce - Developer Edition";
		String actualTitleAfterClickingOther = randomScenariosPage.doClickOnOther();
		Assert.assertEquals(expectedTitleAfterClickingOther, actualTitleAfterClickingOther);

		List<String> expectedList = new ArrayList();
		expectedList.add("9:00 PM");
		expectedList.add("9:30 PM");
		expectedList.add("10:00 PM");
		expectedList.add("10:30 PM");
		expectedList.add("11:00 PM");
		expectedList.add("11:30 PM");

		Assert.assertTrue(randomScenariosPage.doClickOnEndTimeField(expectedList));

		String expectedTimeToBeSelected = "9:00 PM";
		String actualTimeSelected = randomScenariosPage.doSelect9Pm();
		Assert.assertEquals(expectedTimeToBeSelected, actualTimeSelected);

		String expectedPageTitleAfterSave = "Calendar for Divya karna ~ Salesforce - Developer Edition";
		String actualTitleAfterSave = randomScenariosPage.doClickOnSaveInCalendarPage();
		Assert.assertEquals(expectedPageTitleAfterSave, actualTitleAfterSave);

		String expectedSelectedSubject = "Other";
		String actualSelectedSubject = randomScenariosPage.getSelectedSubject();
		Assert.assertEquals(expectedSelectedSubject, actualSelectedSubject);

	}

	//@Test
	public void testCase37() {
		String expectedHomeTitle = "Salesforce - Developer Edition";
		String actualHomeTitle = randomScenariosPage.doClickOnHomeTab();
		Assert.assertEquals(expectedHomeTitle, actualHomeTitle);

		// checking date:need to change expected date every time
		String expectedtodayDate = "Thursday September 8, 2022";
		String actualTodayDate = randomScenariosPage.getTodayDate();
		Assert.assertEquals(expectedtodayDate, actualTodayDate);

		// chrcking calendar page title:it may change with name
		String expectedCalendarPageTitle = "Calendar for Divya karna ~ Salesforce - Developer Edition";
		String actualCalendarPageTitle = randomScenariosPage.doClickOnTodayDate();
		Assert.assertEquals(expectedCalendarPageTitle, actualCalendarPageTitle);
		String expectedNewEventTitle = "Calendar: New Event ~ Salesforce - Developer Edition";
		String actualNewEventTitle = randomScenariosPage.doClickOn4PMLink();
		Assert.assertEquals(expectedNewEventTitle, actualNewEventTitle);

		String expectedComboBoxTitle = "ComboBox";
		String actualComboBoxTitle = randomScenariosPage.doClickOnSubjectComboIcon();
		Assert.assertEquals(expectedComboBoxTitle, actualComboBoxTitle);

		String expectedTitleAfterClickingOther = "Calendar: New Event ~ Salesforce - Developer Edition";
		String actualTitleAfterClickingOther = randomScenariosPage.doClickOnOther();
		Assert.assertEquals(expectedTitleAfterClickingOther, actualTitleAfterClickingOther);

		List<String> expectedList = new ArrayList();
		expectedList.add("5:00 PM");
		expectedList.add("5:30 PM");
		expectedList.add("6:00 PM");
		expectedList.add("6:30 PM");
		expectedList.add("7:00 PM");
		expectedList.add("7:30 PM");
		expectedList.add("8:00 PM");
		expectedList.add("8:30 PM");
		expectedList.add("9:00 PM");
		expectedList.add("9:30 PM");
		expectedList.add("10:00 PM");
		expectedList.add("10:30 PM");
		expectedList.add("11:00 PM");
		expectedList.add("11:30 PM");

		Assert.assertTrue(randomScenariosPage.doClickOnEndTimeField(expectedList));

		String expectedTimeToBeSelected = "7:00 PM";
		String actualTimeSelected = randomScenariosPage.doSelect7Pm();
		Assert.assertEquals(expectedTimeToBeSelected, actualTimeSelected);

		Assert.assertTrue(randomScenariosPage.doClickOnRecurrenceCheckBox());
		// verifying weekly radio button is selected or not
		Assert.assertTrue(randomScenariosPage.doSelectWeeklyRadioButton());
		// verifying recursive every has'1' or not
		Assert.assertTrue(randomScenariosPage.doCheckRecurseEvery());
		// verifying today is selected or not
		String todayDay = "Thursday";
		Assert.assertTrue(randomScenariosPage.currentDayOfTheWeekCheckedOrNot(todayDay));

		String date = "20";
		Assert.assertTrue(randomScenariosPage.doEnterEndDate(date));

		String expectedPageTitleAfterSave = "Calendar for Divya karna ~ Salesforce - Developer Edition";
		String actualTitleAfterSave = randomScenariosPage.doClickOnSaveInCalendarPage();
		Assert.assertEquals(expectedPageTitleAfterSave, actualTitleAfterSave);

		String expectedSelectedSubject = "Other";
		String actualSelectedSubject = randomScenariosPage.getSelectedSubject();
		Assert.assertEquals(expectedSelectedSubject, actualSelectedSubject);

		String expectedMonthViewTitle = "Calendar for Divya karna - Month View ~ Salesforce - Developer Edition";
		String actualMonthViewTitle = randomScenariosPage.doClickOnMonthView();
		Assert.assertEquals(expectedMonthViewTitle, actualMonthViewTitle);

	}

}
