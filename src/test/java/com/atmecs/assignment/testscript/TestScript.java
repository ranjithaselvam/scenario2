package com.atmecs.assignment.testscript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.atmecs.assignment.base.Base;
import com.atmecs.assignment.config.Constant;
import com.atmecs.assignment.helper.Helper;
import com.atmecs.assignment.helper.Waits;
import com.atmecs.assignment.report.Reports;

public class TestScript extends Base {
	public Helper helper = new Helper();
	public Waits waits = new Waits();
	public Reports report = new Reports();

	@BeforeClass
	public void browserLaunch() {
		getBrowser();
		report.logInfo("browser opened");
		getUrl();
		report.logInfo("entered url");
	}

	/**
	 * products are adding into cart and validating the
	 * products(name,size,colour,price,total price)
	 * 
	 * @throws Exception
	 */
	@Test(priority=2)
	public void productValidation() throws Exception {
		waits.implicitlyWait(driver);

		report.logInfo("user landed successfully");
		helper.move(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_merchandise"));
		report.logInfo("moved to merchandise menu");

		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_men"));
		report.logInfo("selected men in drop down list");

		helper.elementIsDisplayed(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_viewing"));
		report.logInfo("viewing mens is displayed");

		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_buynow"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_colour"));
		report.logInfo("selected red colour");

		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_size"));
		report.logInfo("selected middle size");

		helper.inputValuesToTheWebelement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_name"),
				"ranjitha");
		report.logInfo("entered user name");

		helper.scrollToBottomOfThePage(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_popup_buynow"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_popup_buynow"));
		Thread.sleep(2000);

		report.logInfo("navigated to cart");
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_cart"));

		// actual data from website
		String actualItemName = helper.getText(driver,
				utils.propertyRead(Constant.merchandiseMenu_file, "loc_productname"));
		String actualSize = helper.getText(driver,
				utils.propertyRead(Constant.merchandiseMenu_file, "loc_productsize"));
		String actualColour = helper.getText(driver,
				utils.propertyRead(Constant.merchandiseMenu_file, "loc_productcolour"));
		String actualUserName = helper.getText(driver,
				utils.propertyRead(Constant.merchandiseMenu_file, "loc_personalizedname"));
		String actualPrice = helper.getText(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_price"));
		String actualTotalPrice = helper.getText(driver,
				utils.propertyRead(Constant.merchandiseMenu_file, "loc_totalprice"));
		String expectedItemName = utils.propertyRead(Constant.cartProductData_file, "productname");
		String expectedActualSize = utils.propertyRead(Constant.cartProductData_file, "productsize");
		String expectedColour = utils.propertyRead(Constant.cartProductData_file, "productcolour");
		String expectedUserName = utils.propertyRead(Constant.cartProductData_file, "username");
		String expectedPrice = utils.propertyRead(Constant.cartProductData_file, "productprice");
		String expectedTotalPrice = utils.propertyRead(Constant.cartProductData_file, "producttotalprice");
		helper.pageValidation(actualItemName, expectedItemName);
		helper.pageValidation(actualSize, expectedActualSize);
		helper.pageValidation(actualColour, expectedColour);
		helper.pageValidation(actualUserName, expectedUserName);
		helper.pageValidation(actualPrice, expectedPrice);
		helper.pageValidation(actualTotalPrice, expectedTotalPrice);

		report.logInfo("verified product");

		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_quantity"));
		report.logInfo("increased quantity");

		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_update"));
		report.logInfo("updated increased quantity");
		String actualUpdatedTotalPrice = helper.getText(driver,
				utils.propertyRead(Constant.merchandiseMenu_file, "loc_totalprice"));


		String expectedUpadedtotalprice = utils.propertyRead(Constant.cartProductData_file, "updatedtotalprice");
		helper.pageValidation(actualUpdatedTotalPrice, expectedUpadedtotalprice);
	
		

	}

	/**
	 * verify user redirected to respective pages
	 * 
	 */

	
	  @Test (priority=1)
	  public void PageRedirection() {
	  
	  
	  helper.move(driver, utils.propertyRead(Constant.homePage_file,"loc_home")); 
	  report.logInfo("user in home menu");
	  
	  helper.move(driver, utils.propertyRead(Constant.homePage_file,"loc_hotsauces"));
	  report.logInfo("user in hotsauces menu");
	  
	  helper.move(driver, utils.propertyRead(Constant.homePage_file, "loc_merchandise")); 
	  report.logInfo("user in merchandise menu");
	 
	 helper.move(driver, utils.propertyRead(Constant.homePage_file, "loc_clearence")); 
	 report.logInfo("user in clearence menu");
	
	  helper.move(driver, utils.propertyRead(Constant.homePage_file,"loc_newtohotsauces")); 
	  report.logInfo("user in newtohotsauces menu");
	  
	  helper.move(driver, utils.propertyRead(Constant.homePage_file,"loc_faq"));
	  report.logInfo("user in faq menu"); 
	  }
	 

	@AfterClass
	public void browserClose() {

		driverClose();
		report.logInfo("driver closed");
	}

}
