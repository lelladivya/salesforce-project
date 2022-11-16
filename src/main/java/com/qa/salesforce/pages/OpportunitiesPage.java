package com.qa.salesforce.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qa.salesforce.utilities.ElementUtil;

public class OpportunitiesPage {
	WebDriver driver;
	ElementUtil eleUtil;
	By opportunities = By.id("Opportunity_Tab");
	By opportunitiesDropDown = By.xpath("//select[@id='fcf']");
	// testcase 16
	By newele = By.xpath("//td//input[@name='new']");
	By opportunityName = By.xpath("//td//div//input[@name='opp3']");
	By accountName = By.xpath("//span//input[@name='opp4']");
	By closeDate = By.xpath("//span[@class='dateFormat']/a");
	By stage = By.xpath("//select[@id='opp11']");
	By probability = By.xpath("//td//input[@id='opp12']");
	By primaryCampaignSource = By.xpath("//span//input[@id='opp17']");
	By save = By.xpath("//td//input[@name='save']");

	// testcase 17
	By opportunityPipeLine = By.xpath("//li/a[text()='Opportunity Pipeline']");
	By reports = By.id("report_Tab");

	// testCase 18
	By stuckOpportunities = By.xpath("//li/a[text()='Stuck Opportunities']");
//testCase 19
	By interval = By.xpath("//td//select[@id='quarter_q']");
	By include = By.xpath("//td//select[@id='open']");
	By runReport = By.xpath("//td//input[@value='Run Report']");

	public OpportunitiesPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}

	// testcase 15
	public String doClickOnOpportunities() {
		eleUtil.doClick(opportunities);
		String title = eleUtil.waitForTitleContains("Opportunities:", 10);
		return title;
	}

	public List<String> getOpportunitiesDropDownValues() {
		WebElement opportunitiesDropDownEle = eleUtil.waitForElementVisible(opportunitiesDropDown, 10);
		Select select = new Select(opportunitiesDropDownEle);
		List<WebElement> dropDownElements = select.getOptions();
		List<String> dropDownList = new ArrayList<String>();
		for (WebElement e : dropDownElements) {
			dropDownList.add(e.getText());
		}
		return dropDownList;
	}

	// testcase16:
	public String createNewOpportunity(String opporName) {
		doClickOnOpportunities();
		eleUtil.waitForElementVisible(newele, 10);
		eleUtil.doClick(newele);
		WebElement opportunityNameEle = eleUtil.waitForElementVisible(opportunityName, 10);
		opportunityNameEle.sendKeys(opporName);
		eleUtil.doSendKeys(accountName, "My Account");
		eleUtil.doClick(closeDate);
		WebElement stageEle = eleUtil.getElement(stage);
		Select select = new Select(stageEle);
		select.selectByVisibleText("Prospecting");
		WebElement probabilityEle = eleUtil.getElement(probability);
		probabilityEle.clear();
		probabilityEle.sendKeys("20");
		eleUtil.doSendKeys(primaryCampaignSource, "My Source");
		eleUtil.doClick(save);
		String pageTitle = eleUtil.waitForTitleContains(opporName, 10);
		return pageTitle;
	}

	// testCase 17:
	public String DoClickOnOpportunitiesPipeLine() {
		doClickOnOpportunities();
		WebElement pipelineEle = eleUtil.waitForElementVisible(opportunityPipeLine, 10);
		pipelineEle.click();
		WebElement ReportsEle = eleUtil.waitForElementVisible(reports, 10);
		String title = null;
		if (ReportsEle.getText().contains("(Currently Selected)")) {
			title = eleUtil.waitForTitleContains("Opportunity Pipeline", 10);
		}
		return title;

	}

	// testCase 18:
	public String doClickOnStuckOpportunities() {
		doClickOnOpportunities();
		WebElement stuckOpportunitiesEle = eleUtil.waitForElementVisible(stuckOpportunities, 10);
		stuckOpportunitiesEle.click();
		WebElement ReportsEle = eleUtil.waitForElementVisible(reports, 10);
		String title = null;
		if (ReportsEle.getText().contains("(Currently Selected)")) {
			title = eleUtil.waitForTitleContains("Stuck Opportunities", 10);
		}
		return title;

	}

	// testCase 19:
	public String doClickOnQuaterlySummaryLink(String intervalValue, String includeValue) {
		doClickOnOpportunities();
		WebElement intervalEle = eleUtil.waitForElementVisible(interval, 10);
		Select selectInterval = new Select(intervalEle);
		selectInterval.selectByVisibleText(intervalValue);
		WebElement includeEle = eleUtil.getElement(include);
		Select selectInclude = new Select(includeEle);
		selectInclude.selectByVisibleText(includeValue);

		eleUtil.doClick(runReport);
		WebElement rangeEleInReport = eleUtil.waitForElementVisible(By.xpath("//td//select[@id='quarter_q']"), 10);
		Select selectRange = new Select(rangeEleInReport);
		WebElement selectedValue = selectRange.getFirstSelectedOption();
		return selectedValue.getText();
	}
}
