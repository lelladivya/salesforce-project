package com.qa.salesforce.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.salesforce.utilities.ElementUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author Divya this class is to initialize the driver and properties object
 *
 */
public class BasePage {
	static WebDriver driver;
	Properties prop;
	ChromeOptions co;
	// ElementUtil eleUtil;
	public static Logger log = Logger.getLogger(BasePage.class);

	/*
	 * this method is to initialize the driver based on the browser name given in
	 * the properties class
	 * 
	 * @param Properties object
	 * 
	 * @return WebDriver
	 */
	public WebDriver init_Driver(Properties prop) {

		switch (prop.getProperty("browser").trim()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			co = new ChromeOptions();
			if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
				// co.setHeadless(true);
				co.addArguments("--headless");
				driver = new ChromeDriver(co);
				log.info("chrome browser started in headless mode");
			}
			if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
				co.addArguments("--incognito");
				driver = new ChromeDriver(co);
				log.info("chrome browser started in incognito mode");
			}
			driver = new ChromeDriver();
			log.info("chrome browser started successfully");
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			co = new ChromeOptions();

			if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
				// co.setHeadless(true);
				co.addArguments("--headless");
				driver = new EdgeDriver(co);
			}
			if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
				co.addArguments("--incognito");
				driver = new EdgeDriver(co);
			}
			driver = new EdgeDriver();
			log.info("edge browser started");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			co = new ChromeOptions();

			if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
				// co.setHeadless(true);
				co.addArguments("--headless");
				driver = new FirefoxDriver(co);
			}
			if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
				co.addArguments("--incognito");
				driver = new FirefoxDriver(co);
			}
			driver = new FirefoxDriver();
			log.info("firefox browser started");
			break;
		case "safari":
			WebDriverManager.safaridriver().setup();
			co = new ChromeOptions();

			if (Boolean.parseBoolean(prop.getProperty("headless").trim())) {
				// co.setHeadless(true);
				co.addArguments("--headless");
				driver = new SafariDriver(co);
			}
			if (Boolean.parseBoolean(prop.getProperty("incognito").trim())) {
				co.addArguments("--incognito");
				driver = new SafariDriver(co);
			}
			driver = new SafariDriver();
			log.info("safari browser get started");
			break;
		default:
			System.out.println("please pass the right browser name ");
			break;
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("url"));

		return driver;
	}

	/*
	 * this method is to initialize the properties file
	 * 
	 * @return Properties Object
	 */
	public Properties init_Properties() {

		prop = new Properties();
		String filePath = "./src/test/resources/config/config.properties";
		try {
			FileInputStream fs = new FileInputStream(filePath);

			prop.load(fs);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	/*
	 * this method is to take screenshots and to keep screenshots in the specified
	 * file
	 * 
	 * @return this method return the String path of the screenshot file.
	 */

	public static String getScreenShot() {
		String dateFormat = new SimpleDateFormat("yyyy_MM_dd_HHmmss").format(new Date());
		String destinationpath = System.getProperty("user.dir") + "//screenshots//" + dateFormat + "_sfdc.PNG";
		TakesScreenshot ts = (TakesScreenshot) driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		// String filePath = "./screenshot/" + System.currentTimeMillis() + ".png";

		File target = new File(destinationpath);
		try {
			FileUtils.copyFile(src, target);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationpath;
	}

}
