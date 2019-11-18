package com.atmecs.assignment.config;

/**
 * Purpose :To maintain all path as constant for reusable
 * 
 * @author ranjitha.selvam
 *
 */

public class Constant {

	/**
	 * find element specified time
	 * 
	 */
	public  final static long element_wait = 30;
	public  final static long polling_wait = 5;

	/**
	 * different browser drivers path
	 * 
	 */
	public static final String chrome_file = "./drivers/chromedriver.exe";
	public static final String fireFox_file = "./drivers/geckodriver.exe";
	public static final String internetExplorer_file = "./drivers/IEDriverServer.exe";
	public static final String config_file = "./config.properties";
	public static final String log4j_file = "./log4j.properties";

	/**
	 * Data base connection credentials
	 * 
	 */
	public static final String dbUrl = "jdbc:mysql://localhost:3306/demo";
	public static final String dbUserName = "root";
	public static final String dbPassword = "Selv@mm@lli.63";

	/**
	 * heatclinic files path
	 * 
	 */
	public static final String heatClinicHomePage_file = "./src/test/resources/HeatClinicResource/pageobject/homepagemenu/homepage.properties";
	public static final String merchandiseMenu_file = "./src/test/resources/HeatClinicResource/pageobject/merchandisemenu/menu.properties";
	public static final String cartProductData_file = "./src/test/resources/HeatClinicResource/testdata/searchproduct/cartpoduct.properties";

	/**
	 * ninja store resource path
	 * 
	 */
	public final static String ninjaStoreHomePage_file = "./src/test/resources/NinjaStoreResource/pageobject/productVerification/homepage.properties";
	public final static String searchProduct_file = "./src/test/resources/NinjaStoreResource/pageobject/productVerification/searchproduct.properties";
	public final static String productData_file = "./src/test/resources/NinjaStoreResource/testdata/ProductData/searchproduct.xlsx";

}
