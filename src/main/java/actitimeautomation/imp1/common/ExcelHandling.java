package com.actitimeautomation.common;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelHandling {

    /*
     * Get the access of workbook based on the provided file path
     * @Param : filePath
     * */
    public Workbook getWorkBook(String filePath) {
        Workbook workbook = null;
        try {
            String extension = filePath.substring(filePath.indexOf(".") + 1);
            System.out.println(extension);
            //access file
            FileInputStream inputStream = new FileInputStream(filePath);

            //based on the extension of file, access the work of excel file
            if (extension.equals("xlsx")) {
                workbook = new XSSFWorkbook(inputStream);
            } else if (extension.equals("xlx")) {
                workbook = new HSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }

    //get the access of sheet with the reference of workbook

    public Object[][] getExcelData(String filePath, String sheetName) throws IOException {
        Workbook workbook = getWorkBook(filePath);
        //get the control of sheet
        Sheet sheet = workbook.getSheet(sheetName);
        //get total num of rows
        int totalRows = sheet.getPhysicalNumberOfRows();

        //get total columns from 0th row
        int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells();

        //two dimensional array
        Object[][] testData = new Object[totalRows - 1][totalColumns];

        for (int i = 1; i <= totalRows - 1; i++) {

            Row row = sheet.getRow(i);

            for (int j = 0; j <= totalColumns - 1; j++) {
                Cell cell = row.getCell(j);

                //check the type of cell
                CellType cellType = cell.getCellType();

                Object value = null;

                //get the value from cell based on the type
                if (cellType.equals(CellType.NUMERIC)) {

                    value = cell.getNumericCellValue();

                } else if (cellType.equals(CellType.STRING)) {

                    value = cell.getStringCellValue();

                } else if (cellType.equals(CellType.BOOLEAN)) {

                    value = cell.getBooleanCellValue();
                } else {
                    value = " ";
                }

                testData[i - 1][j] = value;
                System.out.print(value + " ");

            }
            System.out.println();
        }

        System.out.println(testData);
        //close workbook
        workbook.close();
        return testData;
    }


    public void writeExcelData(String filePath, String sheetName, Object[][] data) throws IOException {
        Workbook workbook = getWorkBook(filePath);
        Sheet sheet;
        Row row;
        Cell cell;

        //check if the sheet is already created
        //if its created then reuse it else create new with provided name
        if (workbook.getSheet(sheetName) != null) {

            sheet = workbook.getSheet(sheetName);
        } else {
            sheet = workbook.createSheet(sheetName);
        }

        //get the row length of data object
        int totalRowsInData = data.length;

        //get the column length of data object
        int totalColumnsInData = data[0].length;

        //iterate through each row and column of data object and get the value and write into excel
        for (int i = 0; i <= totalRowsInData - 1; i++) {
            //get the access of each row
            //if the row is already present then reuse it else create new row
            if (sheet.getRow(i) != null) {
                row = sheet.getRow(i);
            } else {
                row = sheet.createRow(i);
            }

            for (int j = 0; j <= totalColumnsInData - 1; j++) {

                Object value = data[i][j];


                //if the cell is already created then reuse it else create new cell
                if (row.getCell(j) != null) {
                    cell = row.getCell(j);
                } else {
                    cell = row.createCell(j);
                }
                //set value in cell
                cell.setCellValue(value.toString());
            }
        }
        //create object of FileOutputStream to write or save the file
        FileOutputStream outputStream = new FileOutputStream(filePath);

        //write or save the data workbook using FileOutputStream reference
        workbook.write(outputStream);

        //close the workbook
        workbook.close();

        //close the file output stream
        outputStream.close();
    }

}
