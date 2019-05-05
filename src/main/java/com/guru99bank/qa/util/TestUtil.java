package com.guru99bank.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;

import com.guru99bank.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	
	public TestUtil() throws IOException {
		super();
		
	}
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static String EXPECT_LOGIN_POPUP_ERROR = "User or Password is not valid";
	public static String EXPECT_HOMEPAGE_TITLE = "Guru99 Bank Manager HomePage";
	public static String PAGETEXT_PATTERN= ":";
	public static String PAGETEXT_FIRST_PATTERN="mngr";
	public static String PAGETEXT_SECOND_PATTERN="[0-9]+";
	
	public static String TESTDATA_SHEET_PATH = "E:\\Automation\\Selenium\\Scripts\\Guru99Bank\\src\\main\\java\\com\\"
			+ "guru99bank\\qa\\testdata\\guru99BankTestData.xlsx";

	static Workbook book;
	static Sheet sheet;
	
	public static Object[][] getTestData(String sheetName) throws InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		// System.out.println(sheet.getLastRowNum() + "--------" +
		// sheet.getRow(0).getLastCellNum());
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	}
	
	public static void takeScreenshot() throws IOException
	{
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		// now copy the  screenshot to desired location using copyFile 
		FileUtils.copyFile(src,new File("E:\\Automation\\Selenium\\Screenshots\\output.jpg"));
		
		
	}
	
	

}




