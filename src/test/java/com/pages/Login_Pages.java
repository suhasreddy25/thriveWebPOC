package com.pages;

import java.util.Properties;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.driver.Driver;
import com.utilities.ReadPropertiesFile;

public class Login_Pages extends Driver {
	
	public static final String filename = null;
	public ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();
	public Properties prop = readPropertiesFile.readPropertiesFile(filename);
	
	@FindBy(id = "input-74")
	WebElement email_TextInput;

	@FindBy(id = "input-77")
	WebElement password_Input;
	
	@FindBy(xpath = "//div[contains(text(),'Log In')]")
	WebElement home_login_Button;
	
	@FindBy(xpath = "(//div[contains(text(),'Invalid Credentials')])[2]")
	WebElement error_msg;

	@FindBy(xpath = "(//span[contains(text(),'OK')])[2]")
	WebElement OK_button;
	
	@FindBy(xpath = "//p[contains(text(),'Good food, good mood. Order Now!')]")
	WebElement welcome_message;
	
	@FindBy(xpath = "//p[contains(text(),'Log In')]")
	WebElement login_Button;
	
	@FindBy(xpath = "(//p[contains(text(),'Close')])[2]")
	WebElement close_Button;
	
	@FindBy(xpath = "//div[contains(text(),'Menu')]")
	WebElement menu_Button;
	
	@FindBy(xpath = "//span[contains(text(),'Log Out')]")
	WebElement logout_Button;
	
	public Login_Pages() {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void email_Field(String email) {
		email_TextInput.sendKeys(email);
	}
	
	public void password_Field(String password) {
		password_Input.sendKeys(password);
	}

	public void login_Button_Field() {
		home_login_Button.click();
	}
	
	public String getErrorMessage() {
		return error_msg.getText();
	}
	
	public void ok_button_Field() {
		OK_button.click();
	}
	
	public void clear_email_Field() {
		email_TextInput.clear();
	}
	
	public void clear_password_Field() {
		password_Input.clear();
	}
	
	public String getWelcomeMessage() {
		return welcome_message.getText();
	}
	
	public void login_button() {
		login_Button.click();
	}
	
	public void close_button_Field() {
		close_Button.click();
	}
	
	public void menu_button_Field() {
		menu_Button.click();
	}
	
	public void logout_button_Field() {
		logout_Button.click();
	}
}
