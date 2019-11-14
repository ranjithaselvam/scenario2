package com.atmecs.assignment.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * purpose:Read data from external file (property reader,excel reader,csv
 * reader)
 * 
 * @author ranjitha.selvam
 */

public class Utils {
	public FileInputStream stream;
	public Properties propertyFile = new Properties();
	public Workbook book;
	public Sheet sheet;

	/**
	 * Property file Reader(read the data from property file)
	 * 
	 */
	public String propertyRead(String path, String elements) {
		String data = null;
		propertyFile = new Properties();
		FileInputStream reader = null;
		try {
			reader = new FileInputStream(path);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		try {
			propertyFile.load(reader);
			data = propertyFile.getProperty(elements);

		} catch (IOException e) {

			e.printStackTrace();
		}
		return data;
	}

	/**
	 * Take the value from excel file and passing the value to web site. Apache poi
	 * supports to read value from excel file.
	 * 
	 * @param path
	 * @param sheetName
	 * @return
	 */

	public Object[][] getExcel(String path, String sheetName) {
		File file = new File(path);
		FileInputStream read = null;
		try {
			read = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(read);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int rowCount = 0; rowCount < sheet.getLastRowNum(); rowCount++) {
			for (int cellCount = 0; cellCount < sheet.getRow(0).getLastCellNum(); cellCount++) {
				data[rowCount][cellCount] = sheet.getRow(rowCount + 1).getCell(cellCount).toString();
			}
		}
		return data;

	}

	/**
	 * Read specific cell value via excel reader
	 * 
	 * @param path
	 */
	@SuppressWarnings("resource")
	public void ReadExcel(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(0);
			Cell cell = row.getCell(0);
			System.out.println(cell);
			System.out.println(sheet.getRow(0).getCell(0));
			String cellval = cell.getStringCellValue();
			System.out.println(cellval);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void fetchValuesFromDataBaseUsingJdbc(String url,String userName,String password,String query,String columnName)
	{
		try {
			List<String>li=new ArrayList<>();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection(  
					url,userName,password);   
					Statement stmt=con.createStatement();
					ResultSet rs=stmt.executeQuery(query);
					while(rs.next())
					{
						String data = rs.getString(columnName);
						li.add(data);
					}
					con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}  
	  
	}
	
	
	
}
