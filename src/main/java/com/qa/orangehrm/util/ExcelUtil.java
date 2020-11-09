package com.qa.orangehrm.util;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	/*
	 * ExcelUtil class is created to communicate with my test class and external excel file.
	 * 
	 * ExcelUtil class provides to read my data from Excel Sheet 
	 * 
	 * This class is using import org.apache.poi, Apache poi provides to read and write any data from the external resources, especially 
	 * excel file. For this reason we created workbook and sheet, they come from excel file
	 * 
	 * 
	 * Question: how can i create a logic to read data, because we said workbook and sheet, also Apache provides to read data,
	 * how they read, there must be a logic , so what is the logic ? 
	 * 
	 * All Excel sheet consist of column and row, so we need to create dimensional array which read the data from excel sheet.
	 * 
	 * 
	 * 
	 */
	
	
	public static Workbook book; // comes from excel, all excel file consist of workbook and sheet, they provide to read data
	public static Sheet sheet;  // comes from excel
	
	public static String TESTDATA_SHEET_DATA = "/Users/dilhumarablet/Documents/workspace/OrganeHRMPlatform_C4"
			+ "/src/main/java/com/qa/orangehrm/testdata/HRMData.xlsx";
	
	public static Object[][] getTestData(String sheetName) {
		
		try {
			
			FileInputStream ip = new FileInputStream(TESTDATA_SHEET_DATA);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
			for(int i = 0; i < sheet.getLastRowNum(); i++) {
				for(int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
					data[i][k] = sheet.getRow(i+1).getCell(k).toString();
				}
			}
			return data;
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (InvalidFormatException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
