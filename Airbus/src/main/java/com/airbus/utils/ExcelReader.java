package com.airbus.utils;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.stereotype.Component;

import com.airbus.entitu.Product;

@Component
public class ExcelReader {

	
	

	   
	  public static Product readProduct(Row row, FormulaEvaluator evaluator, Map<String, Integer> headerMap) {
	        try {
	        	
	            String name = getCellValue(row.getCell(headerMap.get("Name".toLowerCase())));
	            String type1 = getCellValue(row.getCell(headerMap.get("Type1".toLowerCase())));
	            String type2 = getCellValue(row.getCell(headerMap.get("Type2".toLowerCase())));
	            int hp = Integer.parseInt(getCellValue(row.getCell(headerMap.get("HP".toLowerCase()))));
	            int column1 = Integer.parseInt(getCellValue(row.getCell(headerMap.get("Column1".toLowerCase()))));
	            int column2 = Integer.parseInt(getCellValue(row.getCell(headerMap.get("Column2".toLowerCase()))));

	            return new Product(name, type1, type2, hp, column1, column2);
	        } catch (Exception e) {
	            throw new RuntimeException("Error reading product: " + e.getMessage(), e);
	        }
	    }

	    private static String getCellValue(Cell cell) {
	        if (cell == null) return "";
	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue().trim();
	            case NUMERIC:
	                return String.valueOf((int) cell.getNumericCellValue()); // Assuming numeric values are integers
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula();
	            default:
	                return "";
	        }
	    }
	
	   public static boolean isRowEmpty(Row row){
	        for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {
	            Cell cell = row.getCell(cellNum);
	            if (cell != null && cell.getCellType() != CellType.BLANK) {
	                return false;
	            }
	        }
	        return true;
	    }
}
