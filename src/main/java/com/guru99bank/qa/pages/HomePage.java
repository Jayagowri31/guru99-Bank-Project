package com.guru99bank.qa.pages;



import java.io.IOException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.guru99bank.qa.base.TestBase;


public class HomePage extends TestBase {

	@FindBy(tagName="tbody")
	WebElement managerid;
	
	
	
	public HomePage() throws IOException {
		PageFactory.initElements(driver,this);
	}
	
	
		
		
		
		
	}
	
	
	

