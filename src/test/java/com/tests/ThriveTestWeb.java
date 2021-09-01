package com.tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.driver.Driver;
import com.pages.Login_Pages;
import com.utilities.ReadPropertiesFile;

public class ThriveTestWeb extends Driver {

	public static final String fileName = null;
	public ReadPropertiesFile readfile = new ReadPropertiesFile();
	public Properties prop = readfile.readPropertiesFile(fileName);

	public Login_Pages login_pages;
	
	@BeforeSuite
	public void beforeSuite() throws IOException {
		Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe /T");
	}

	@BeforeClass
	public void initialization() throws MalformedURLException {
		login_pages = new Login_Pages();
		Driver.init(prop.getProperty("Browser"));
		Driver.driver.get(prop.getProperty("URL"));
	}

	@Test(priority = 0)
	public void unsuccessful_Login() {
		login_pages = new Login_Pages();
		login_pages.login_Button_Field();
		login_pages.clear_email_Field();
		login_pages.email_Field("suhasr.m25@gmail.com");
		login_pages.password_Field("12345");
		login_pages.login_button();
		Driver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		String error_message = login_pages.getErrorMessage();
		Assert.assertEquals("Invalid Credentials", error_message);
		login_pages.ok_button_Field();
	}

	@Test(priority = 1)
	public void successful_Login() {
		login_pages = new Login_Pages();
		login_pages.close_button_Field();
		Driver.driver.navigate().refresh();
		Driver.driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		login_pages.login_Button_Field();
		login_pages.clear_email_Field();
		login_pages.email_Field("suhasr.m25@gmail.com");
		login_pages.clear_password_Field();
		login_pages.password_Field("Suhas@2021#*");
		login_pages.login_button();
		login_pages.ok_button_Field();
		String welcome_msg = login_pages.getWelcomeMessage();
		Assert.assertEquals("Good food, good mood. Order Now!", welcome_msg);
		login_pages.menu_button_Field();
		login_pages.logout_button_Field();
	}
	
	@AfterMethod
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				TakesScreenshot ts = (TakesScreenshot) driver;
				File source = ts.getScreenshotAs(OutputType.FILE);
				FileHandler.copy(source, new File("./Screenshots/" + result.getName() + ".jpg"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@AfterClass
	public void quit() {
		Driver.driver.quit();
	}
}
