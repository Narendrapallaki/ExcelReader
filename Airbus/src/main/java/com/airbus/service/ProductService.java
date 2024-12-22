package com.airbus.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.airbus.entitu.Product;
import com.airbus.utils.ExcelReader;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Value("${excel.path.atm-excel-filepath}")
	public String atmExcelFilePath;
	
	public static final String FORE_CAST_SHEET="PRODUCT";

	public List<Product> atmDateExcelSheetReader() throws IOException {

		System.out.println("Entry of atmDateExcelSheetReader case  : ");
		List<Product> listOfProduct1 = new ArrayList<>();

		try (XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(atmExcelFilePath))) {
			FormulaEvaluator formulaEvaluator = workbook.getCreationHelper().createFormulaEvaluator();
			for (Sheet sheet : workbook) {
				String sheetName = sheet.getSheetName().toUpperCase().replaceAll("\\s+", "");
				System.out.println("Sheet name : " + sheetName);
				// Use the generic method to process the sheet based on its name
				List<Product> sheetData = processSheetData(sheet, formulaEvaluator, sheetName);
				for (Product product : sheetData) {
					listOfProduct1.add(product);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return listOfProduct1;
	}

	public List<Product> processSheetData(Sheet sheet, FormulaEvaluator formulaEvaluator, String sheetName) {
		System.out.println("Entry of processSheetData  : ");
		List<Product> listOfProduct = new ArrayList<>();

		switch (sheetName) {
		case FORE_CAST_SHEET:
			System.out.println("Entry of product case  : ");
			for (Product product : processForecastSheetSheet(sheet, formulaEvaluator)) {

				System.out.println("Entry of product case  : "+1);
				listOfProduct.add(product);
			}
			break;

		default:
			System.out.println("Entry of no case  : ");
			return null; // Early exit if no matching case
		}

		return listOfProduct;
	}
	public List<Product> processForecastSheetSheet(Sheet sheet, FormulaEvaluator evaluator) {
	    System.out.println("Entry of processAtmShortageSheet case:");
	    List<Product> atmShortageList = new ArrayList<>();

	    // Read the header row
	    Row headerRow = sheet.getRow(0);
	    if (headerRow == null) {
	        throw new IllegalArgumentException("The header row is missing in the sheet.");
	    }

	    // Create a map to store header names and their corresponding column indices
	    Map<String, Integer> headerMap = new HashMap<>();
	    for (Cell cell : headerRow) {
	        // Only process non-empty headers
	        if (cell != null && cell.getCellType() == CellType.STRING && !cell.getStringCellValue().trim().isEmpty()) {
	            String headerValue = cell.getStringCellValue().trim().toLowerCase();
	            headerMap.put(headerValue, cell.getColumnIndex());
	        }
	    }
          System.out.println("Sheet Headers :"+headerMap);
	    // Required headers
	    String[] requiredHeaders = { "Name", "Type1", "Type2", "HP", "Column1", "Column2" };

	    // Validate that all required headers exist in the map
	    for (String header : requiredHeaders) {
	        if (!headerMap.containsKey(header.toLowerCase())) {
	            throw new IllegalArgumentException("Missing required header: " + header);
	        }
	    }

	    int emptyRowCount = 0;
	    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
	        System.out.println("Processing row: " + i);
	        Row row = sheet.getRow(i);
   
	        // Check if the row is empty
	        if (row == null || ExcelReader.isRowEmpty(row)) {
	            emptyRowCount++;
	            if (emptyRowCount == 10) {
	                break; // Stop processing after 10 consecutive empty rows
	            }
	            continue;
	        }

	        try {
	            Product product = ExcelReader.readProduct(row, evaluator, headerMap);
	            atmShortageList.add(product);
	        } catch (Exception e) {
	            System.err.println("Error processing row " + i + ": " + e.getMessage());
	        }
	    }

	    return atmShortageList; // Return the populated list
	}


}
