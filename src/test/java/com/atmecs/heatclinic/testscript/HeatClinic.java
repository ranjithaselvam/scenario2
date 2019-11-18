package com.atmecs.heatclinic.testscript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.atmecs.assignment.base.Base;
import com.atmecs.assignment.config.Constant;
import com.atmecs.assignment.helper.Helper;
import com.atmecs.assignment.helper.Waits;
import com.atmecs.assignment.report.Reports;

public class HeatClinic extends Base {
	public Helper helper = new Helper();
	public Waits waits = new Waits();
	public Reports report = new Reports();

	@BeforeClass
	public void browserLaunch() {
		report.logInfo("#Step 1: Browser opening");
		getBrowser(utils.propertyRead(Constant.config_file, "browserName"));
		report.logInfo("#Step 2: Entering  url");
		getUrl(utils.propertyRead(Constant.config_file, "heatClinicUrl"));

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

		report.logInfo("#Step 3: User landed successfully in home page");
		report.logInfo("#Step 4: Select \"MERCHANDISE > MENS\" from the tabs in header");
		helper.move(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_merchandise"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_men"));

		report.logInfo("#Step 5:Verify \"Viewing Mens\" text is displayed");
		helper.elementIsDisplayed(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_viewing"));

		report.logInfo("#Step 6:Now select \"BUY NOW\" for product -   Hawt Like a Habanero Shirt (Men's)  ");
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_buynow"));

		report.logInfo("#Step 7:Select color (red) and size (M) from modal popup window");
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_colour"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_size"));

		report.logInfo("#Step 8:Enter Personalized name");
		helper.inputValuesToTheWebelement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_name"),
				"ranjitha");

		report.logInfo("#Step 9:Click on \"Buy Now\"");
		helper.scrollToBottomOfThePage(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_popup_buynow"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_popup_buynow"));

		report.logInfo(
				"#Step 10:Navigate to cart and validate item (Hawt Like a Habanero Shirt (Men's) is added to cart.");
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

		// Expected data from database
		report.logInfo("#Step 11:Validate product name");
		String expectedProductName = utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata", 2);
		helper.pageValidation(actualItemName, expectedProductName);

		report.logInfo("#Step 12:Validate product size");
		String expectedSize = utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata", 3);
		helper.pageValidation(actualSize, expectedSize);

		report.logInfo("#Step 13:Validate product colour");
		String expectedColour = utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata", 4);
		helper.pageValidation(actualColour, expectedColour);

		report.logInfo("#Step 14:Validate user name");
		String expectedUserName = utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata", 5);
		helper.pageValidation(actualUserName, expectedUserName);

		report.logInfo("#Step 15:Validate product price");
		String expectedPrice = utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata", 6);
		helper.pageValidation(actualPrice, expectedPrice);

		report.logInfo("#Step 16:Validated product total price");
		String expectedTotalPrice = utils.fetchValuesFromDataBaseUsingJdbc("select * from testdata", 7);
		helper.pageValidation(actualTotalPrice, expectedTotalPrice);

		report.logInfo("#Step 17:Increase the Quantity and Click on the update button");
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_quantity"));
		helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_update"));

		report.logInfo("#Step 18:Verify the total price is updated accordingly");
		String actualUpdatedTotalPrice = helper.getText(driver,
				utils.propertyRead(Constant.merchandiseMenu_file, "loc_totalprice"));
		String expectedUpadedtotalprice = utils.propertyRead(Constant.cartProductData_file, "updatedtotalprice");
		helper.pageValidation(actualUpdatedTotalPrice, expectedUpadedtotalprice);
        helper.clickOnWebElement(driver, utils.propertyRead(Constant.merchandiseMenu_file, "loc_close"));

	}

	/**
	 * verify user redirected to respective pages
	 * 
	 */

	@Test(priority = 2)
	public void PageRedirection() {
		
        report.logInfo("#Step 19:Traverse through all the menus available and verify user redirected to respective pages");
		helper.move(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_home"));
		System.out.println("ok");
	    helper.move(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_hotsauces"));
	    helper.move(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_merchandise"));
		helper.move(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_clearence"));
		helper.move(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_newtohotsauces"));
		helper.move(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_faq"));
		

	}

	@AfterClass
	public void browserClose() {
		report.logInfo("Step 20:driver close");
		driverClose();
		
	}

}
