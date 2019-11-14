package com.atmecs.assignment.excel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atmecs.assignment.config.Constant;




public class ExcelReader {
	 //Main Directory of the project
  
 
    //Location of Test data excel file
    public  String testDataExcelPath = null;
 
    //Excel WorkBook
    private  XSSFWorkbook excelWBook;
 
    //Excel Sheet
    private  XSSFSheet excelWSheet;
 
    //Excel cell
    private  XSSFCell cell;
 
    //Excel row
    private  XSSFRow row;
 
    //Row Number
    public  int rowNumber;
 
    //Column Number
    public  int columnNumber;
 
    //Setter and Getters of row and columns
    public void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }
 
    public  int getRowNumber() {
        return rowNumber;
    }
 
    public  void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }
 
    public  int getColumnNumber() {
        return columnNumber;
    }
 
    // This method has two parameters: "Test data excel file name" and "Excel sheet name"
    // It creates FileInputStream and set excel file and excel sheet to excelWBook and excelWSheet variables.
    public  void setExcelFileSheet(String sheetName) {
        //MAC or Windows Selection for excel path
        try {
            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(testDataExcelPath + Constant.testDataExcelFileName);
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
 
    //This method reads the test data from the Excel cell.
    //We are passing row number and column number as parameters.
    public String getCellData(int RowNum, int ColNum) {
        try {
            cell= excelWSheet.getRow(RowNum).getCell(ColNum);
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } catch (Exception e) {
            throw (e);
        }
    }
 
    //This method takes row number as a parameter and returns the data of given row number.
    public  XSSFRow getRowData(int RowNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }
 
    //This method gets excel file, row and column number and set a value to the that cell.
    public  void setCellData(String value, int RowNum, int ColNum) {
        try {
            row = excelWSheet.getRow(RowNum);
            cell = row.getCell(ColNum);
            if (cell == null) {
                cell = row.createCell(ColNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            // Constant variables Test Data path and Test Data file name
            FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + Constant.testDataExcelFileName );
            excelWBook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            try {
                throw (e);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}



