package com.guru99bank.qa.testcases;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.guru99bank.qa.base.TestBase;
import com.guru99bank.qa.pages.HomePage;
import com.guru99bank.qa.pages.LoginPage;
import com.guru99bank.qa.util.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homepage;

	public LoginPageTest() throws IOException {
		super();

	}

	@BeforeMethod
	public void setUp() throws IOException {
		initializeDriver();
		loginPage = new LoginPage();
		homepage = new HomePage();
	}

	@DataProvider
	public Object[][] getLoginTestData() throws InvalidFormatException {
		Object data[][] = TestUtil.getTestData("Loginpage");
		return data;
	}

	@Test(dataProvider = "getLoginTestData")
	public void validateValidLoginTest(String Username, String Password) throws IOException {
		homepage = loginPage.validateLogin(Username, Password);
		try {
			loginPage.validateLoginError();
			Assert.assertEquals(loginPage.actualLoginPopupError, TestUtil.EXPECT_LOGIN_POPUP_ERROR);

		} catch (NoAlertPresentException exception) {
			loginPage.validateTitle();
			Assert.assertEquals(loginPage.actualHomepageTitle, TestUtil.EXPECT_HOMEPAGE_TITLE);
			// Extract the dynamic text mngrXXXX on page
			String pageText = driver.findElement(By.tagName("tbody")).getText();
			String[] parts = pageText.split(TestUtil.PAGETEXT_PATTERN);
			String dynamicText = parts[1];
			// Check that the dynamic text is of pattern mngrXXXX
			// First 4 characters must be "mngr"
			Assert.assertTrue(dynamicText.substring(1, 5).equals(TestUtil.PAGETEXT_FIRST_PATTERN));
			// remain stores the "XXXX" in pattern mngrXXXX
			String remain = dynamicText.substring(dynamicText.length() - 4);
			// Check remain string must be numbers;
			Assert.assertTrue(remain.matches(TestUtil.PAGETEXT_SECOND_PATTERN));
			TestUtil.takeScreenshot();

		}

	}

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}
