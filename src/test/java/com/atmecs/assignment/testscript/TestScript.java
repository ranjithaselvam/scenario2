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
	@Test(priority = 1)
	public void productValidation() throws Exception {
		waits.implicitlyWait(driver);

		report.logInfo("user landed successfully in home page");
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
		report.logInfo("navigated to cart");

		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_cart"));

		
		  // actual data from website 
		String actualItemName = helper.getText(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_productname")); 
		String actualSize = helper.getText(driver,utils.propertyRead(Constant.merchandiseMenu_file, "loc_productsize"));
		String actualColour = helper.getText(driver,utils.propertyRead(Constant.merchandiseMenu_file, "loc_productcolour"));
		String actualUserName = helper.getText(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_personalizedname"));
		String actualPrice = helper.getText(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_price"));
		String actualTotalPrice = helper.getText(driver,utils.propertyRead(Constant.merchandiseMenu_file, "loc_totalprice"));
		  
		  
		  
		  //Expected data from database
		  String expectedProductName =utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata",2);
		  helper.pageValidation(actualItemName, expectedProductName);
		  report.logInfo("Validated product name");
		  
		  String expectedSize =utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata",3);
		  helper.pageValidation(actualSize, expectedSize);
		  report.logInfo("Validated product size");
		  
		  String expectedColour =utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata",4);
		  helper.pageValidation(actualColour, expectedColour);
		  report.logInfo("Validated product colour");
		  
		  String expectedUserName =utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata",5);
		  helper.pageValidation(actualUserName, expectedUserName);
		  report.logInfo("Validated user name");
		  
		  String expectedPrice =utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata",6);
		  helper.pageValidation(actualPrice, expectedPrice);
		  report.logInfo("Validated product price");
		  
		  String expectedTotalPrice =utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata",7);
		  helper.pageValidation(actualTotalPrice, expectedTotalPrice);
		  report.logInfo("Validated product grand total");
		  report.logInfo("verified product details");
		  
		  
		  helper.clickOnWebElement(driver,utils.propertyRead(Constant.merchandiseMenu_file, "loc_quantity"));
		  report.logInfo("increased quantity");
		  
		  
		  helper.clickOnWebElement(driver,utils.propertyRead(Constant.merchandiseMenu_file, "loc_update"));
		  report.logInfo("updated increased quantity"); 
		  
		  String actualUpdatedTotalPrice = helper.getText(driver, utils.propertyRead(Constant.merchandiseMenu_file,"loc_totalprice"));
		  String expectedUpadedtotalprice =utils.propertyRead(Constant.cartProductData_file, "updatedtotalprice");
		  helper.pageValidation(actualUpdatedTotalPrice, expectedUpadedtotalprice);
		  
		  helper.clickOnWebElement(driver,utils.propertyRead(Constant. merchandiseMenu_file,"loc_close"));
		 
		  
		  
		  }
		  
		 /**
			 * verify user redirected to respective pages
			 * 
			 */
				  
				  @Test(priority = 2) public void PageRedirection() {
				  
				  helper.move(driver, utils.propertyRead(Constant.homePage_file, "loc_home"));
				  report.logInfo("user in home menu");
				  
				  helper.move(driver, utils.propertyRead(Constant.homePage_file, "loc_hotsauces")); 
				  report.logInfo("user in hotsauces menu");
				  
				  helper.move(driver, utils.propertyRead(Constant.homePage_file,"loc_merchandise"));
				  report.logInfo("user in merchandise menu");
				  
				  helper.move(driver, utils.propertyRead(Constant.homePage_file,"loc_clearence")); 
				  report.logInfo("user in clearence menu");
				  
				  helper.move(driver, utils.propertyRead(Constant.homePage_file,"loc_newtohotsauces"));
				  report.logInfo("user in newtohotsauces menu");
				  
				  helper.move(driver, utils.propertyRead(Constant.homePage_file, "loc_faq"));
				  report.logInfo("user in faq menu");
				 
	}

	@AfterClass
	public void browserClose() {

		driverClose();
		report.logInfo("driver closed");
	}

}
