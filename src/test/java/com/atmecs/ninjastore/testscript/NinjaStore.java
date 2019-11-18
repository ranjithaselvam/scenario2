package com.atmecs.ninjastore.testscript;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.atmecs.assignment.base.Base;
import com.atmecs.assignment.config.Constant;
import com.atmecs.assignment.helper.Helper;
import com.atmecs.assignment.helper.Waits;
import com.atmecs.assignment.report.Reports;

public class NinjaStore extends Base {
	/**
	 * Verify and validate the product
	 * 
	 * @author ranjitha.selvam
	 *
	 */
		public Helper helper = new Helper();
		public Waits waits = new Waits();
		public Reports report = new Reports();

		@BeforeClass
		public void browserLaunch() {
			report.logInfo("#Step 1:Browser open");
			getBrowser(utils.propertyRead(Constant.config_file,"browserName"));
			
			report.logInfo("#Step 2:Enter url");
			getUrl(utils.propertyRead(Constant.config_file,"ninjaStoreUrl"));
			

		}
		@Test(priority=1)
		public void homePageValidation()
		{
			report.logInfo("Step 3:Validate Home page");
			helper.elementIsDisplayed(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_home"));
			report.logInfo("validated home page");
			
		}

		@DataProvider
		public Object[][] productSearch() throws Exception {
			Object data[][] = utils.getExcel(Constant.productData_file, "productDetails");
			return data;
		}

		@Test(dataProvider = "productSearch",priority=2)
		public void productValidation(String searchcategories,String availability, String price, String extax,String quantity, String description)
				throws Exception {
			waits.implicitlyWait(driver);
			
			report.logInfo("#Step 4:Search for products  iPhone (Qty: 2) & MacBook Air (Qty: 3) and add products to the cart");
			// search product
			helper.inputValuesToTheWebelement(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_searchBox"),
					searchcategories);
		
			helper.clickOnWebElement(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_searchbutton"));
			helper.scrollToBottomOfThePage(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_product"));
			helper.clickOnWebElement(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_product"));
			
			report.logInfo("#Step 5:Validate the data of each product Availability,Product Price (should be in dollar),Ex Tax and Product description");
					
			String actualAvailability = helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_availability"));
			helper.pageValidation(actualAvailability,availability);
			String actualPrice = helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_price"));
			helper.pageValidation(actualPrice, price);
			String actualExTax = helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_extax"));
			helper.pageValidation(actualExTax, extax);
			String actualDescription = helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_description"));
			helper.pageValidation(actualDescription, description);
			helper.clearText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_quantity"));
			helper.inputValuesToTheWebelement(driver,utils.propertyRead(Constant.searchProduct_file, "loc_quantity"),quantity);
			helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_add"));
			helper.clickOnWebElement(driver, utils.propertyRead(Constant.ninjaStoreHomePage_file, "loc_ninja"));
			
		}
		@Test(priority=3)
		public void ProductDetails() throws Exception
		{
			report.logInfo("Step 6:Verify all added products are available in the cart");
			helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_cart"));
			helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_iphone"));
			helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_macbook"));
			helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_total"));
		
			report.logError("Step 7:Remove one product and verify the grand total");
			helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_remove"));
			helper.clickOnWebElement(driver, utils.propertyRead(Constant.searchProduct_file, "loc_cart"));
            helper.getText(driver, utils.propertyRead(Constant.searchProduct_file, "loc_total"));
			
			

		}

		@AfterClass
		public void browserClose() {

			driverClose();
			report.logInfo("Step 8:driver closed");
		}

	}


