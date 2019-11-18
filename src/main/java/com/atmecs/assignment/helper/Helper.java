package com.atmecs.assignment.helper;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.atmecs.assignment.objectrepository.ObjectRepository;
import com.atmecs.assignment.report.Reports;

public class Helper {

	public Reports report = new Reports();
	public ObjectRepository objRep = new ObjectRepository();


	/**Clear previous text in search box
	 * 
	 * @param driver
	 * @param element
	 */
	public void clearText(WebDriver driver,String element)
	{
		try {
			WebElement webElement = objRep.findObject(driver, element);
			webElement.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Determine the the state of the application whether it is the same what we are
	 * expecting or not.
	 * 
	 * @param actualDetails
	 * @param expectedDetails
	 */

	public void pageValidation(String actualDetails, String expectedDetails) {
		try {

			Assert.assertEquals(actualDetails, expectedDetails);
			report.logInfo(" Passed : " + " Expected : " + expectedDetails + " Actual : " + actualDetails);
		} catch (AssertionError assertionError) {
			report.logInfo(" Failed : " + " Expected : " + expectedDetails + " Actual : " + actualDetails);
		}

	}

	/**
	 * capable to check for the presence of all kinds of web elements available.
	 * used to verify if the web element is enabled or disabled within the web page.
	 * 
	 * @param element
	 * @return
	 */

	public boolean elementIsDisplayed(WebDriver driver, String element) {
		try {
			WebElement text = objRep.findObject(driver, element);
			boolean displayed = text.isDisplayed();
			System.out.println(displayed);
			return displayed;
		} catch (Exception e) {
			e.printStackTrace();

		}
		return false;
	}

	/**
	 * pass input values to the text box(enter the values).
	 * 
	 * @param element
	 * @param values
	 */
	public void inputValuesToTheWebelement(WebDriver driver, String element, String values) {
		try {

			WebElement webElement = objRep.findObject(driver, element);
			webElement.sendKeys(values);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Handling mouse event(Clicks at the current mouse location). Click button or
	 * whatever
	 * 
	 * @param element
	 */

	public void clickOnWebElement(WebDriver driver, String element) {
		try {

			WebElement webElement = objRep.findObject(driver, element);
			webElement.click();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * Retrieving the specified elements Text(Get Text).
	 */

	public String getText(WebDriver driver, String element) throws Exception {
		String text = null;
		try {
			WebElement webElement = objRep.findObject(driver, element);

			text = webElement.getText();

			System.out.println("Actual text :" + text);
			return text;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * Scroll to bottom of the page
	 * 
	 */
	public void scrollToBottomOfThePage(WebDriver driver,String element) {
		try {
			WebElement webElement = objRep.findObject(driver, element);
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", webElement);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}



	/**
	 * mouse over to specific web element
	 * 
	 */
	public void move(WebDriver driver, String element) {

		WebElement mouseOver = objRep.findObject(driver, element);
		Actions action = new Actions(driver);
		action.moveToElement(mouseOver).perform();

	}

	
}
