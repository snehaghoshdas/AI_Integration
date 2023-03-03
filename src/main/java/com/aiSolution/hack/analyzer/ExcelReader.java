package com.aiSolution.hack.analyzer;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ExcelReader {
    public static String cidcheck(String queryText) {
        try {
            FileInputStream excelFile = new FileInputStream(new File("/Users/in-sarang.kulkarni/neha/AI_Integration/Book1.xlsx"));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            String[] tokens = queryText.split(" "); // split the string into tokens based on the space character
            List<String> tokenList = Arrays.asList(tokens);
            for (Row row : sheet) {

                // Get the value in the second column (index 1)
                Cell cell = row.getCell(1);
                String value=null;
                if (cell != null) {

                        switch (cell.getCellType()) {
                            case STRING:
                                value = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                value = String.valueOf(cell.getNumericCellValue());
                                break;
                            case BOOLEAN:
                                value = String.valueOf(cell.getBooleanCellValue());
                                break;
                            case FORMULA:
                                value = cell.getCellFormula();
                                break;
                            default:
                                value = "";
                        }
                    }

                    // Compare the value with the input text
                    if (tokenList.contains(value)) {

                        // If match found, get the value in the first column (index 0)
                        Cell firstColumnCell = row.getCell(0);
                        if (firstColumnCell != null) {
                            String firstColumnValue = firstColumnCell.getStringCellValue();
                            return firstColumnValue;
                        }
                    }
                }
            workbook.close();
            }
        catch (IOException e) {
            e.printStackTrace();
        }



        return null;
    }
}
