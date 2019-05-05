package com.guru99bank.qa.pages;

import java.io.IOException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guru99bank.qa.base.TestBase;

public class LoginPage extends TestBase {

	// WebObjects
	@FindBy(name = "uid")
	WebElement username;

	@FindBy(name = "password")
	WebElement password;

	@FindBy(xpath = "//input[@type='submit']")
	WebElement LoginButton;

	// Initializing the page objects
	public LoginPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	// Actions

	public String actualLoginPopupError;
	public String actualHomepageTitle;
	
	public HomePage validateLogin(String un, String pwd) throws IOException {

		username.sendKeys(un);
		password.sendKeys(pwd);
		LoginButton.click();
		return new HomePage();

	}
	
	public WebDriver validateLoginError() {
		
		Alert alert = driver.switchTo().alert();
		actualLoginPopupError = alert.getText();
		alert.accept();
		return driver;
		
	}
	
	public WebDriver validateTitle() {
		actualHomepageTitle = driver.getTitle();
		return driver;
	}

}
