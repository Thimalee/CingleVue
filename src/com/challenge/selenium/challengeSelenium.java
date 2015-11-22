package com.challenge.selenium;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import com.challenge.utility.constants;


public class challengeSelenium {

	public static void run_code_challenge() throws Exception{
		
		WebDriver driver = new FirefoxDriver();
		
		driver.manage().window().maximize();

		// open URL
		driver.get(constants.URL);
		
		// get the text of the body element		
		List<WebElement> body = driver.findElements(By.tagName("body"));
		
		// search for the String within the text
		StringBuilder stringBuilder = new StringBuilder();

		// count occurrences of the string
		for (int i = 0; i < body.size(); i++) {
			stringBuilder.append(body.get(i).getText());
		}

		String totalResult = stringBuilder.toString();

		System.out.println(totalResult.toLowerCase().split("cinglevue", -1).length - 1);
		
		// get number of occurrences to integer
		int totalResultInInt = totalResult.toLowerCase().split("cinglevue", -1).length - 1;
		
		// save test results to excel
		try{
			FileInputStream file = new FileInputStream(new File(constants.PATH_TESTDATA + constants.FILE_TESTDATA)); 
			
			//get the work book
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			
			//get first sheet of the work book
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			Cell cell = null;
			String testResult;
			
			// verify that there are more than 10 results	
			if (totalResultInInt > 10){
				System.out.println("Occurrences more than 10");
				testResult = "Pass";
			}else{
				System.out.println("Occurrences less than 10");
				testResult = "Failed";
			}
			
			// update test results into cell
			cell = sheet.getRow(1).getCell(3);
			cell.setCellValue(testResult);
			
			FileOutputStream outFile = new FileOutputStream(new File(constants.PATH_TESTDATA + constants.FILE_TESTDATA));
			
			// write results to excel
			workbook.write(outFile);
			workbook.close();
			
		}catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		
		
	}
	
	
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		run_code_challenge();

	}

}
