package com.oleg.parse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.Date;

public class ExcelUtil {

    public static void writeStatusUpToExcel(String file) {

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("E:/test/test.xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook book = null;
        try {
            assert inputStream != null;
            book = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert book != null;
            Sheet sheet = book.getSheet("Sheet0");

            Row row = sheet.getRow(0);
            Cell status = row.createCell(1);
            status.setCellValue("address available");
            Cell data = row.createCell(2);

            Date date = new Date();
            data.setCellValue(date.toString());

            try {
                book.write(new FileOutputStream(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    public static void writeStatusOffToExcel(String file) {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("E:/test/test.xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Workbook book = null;
        try {
            assert inputStream != null;
            book = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert book != null;
        Sheet sheet = book.getSheet("Sheet0");

        Row row = sheet.getRow(0);

        Cell status = row.createCell(1);
        status.setCellValue("address not available");

        Cell data = row.createCell(2);
        Date date = new Date();
        data.setCellValue(date.toString());
        try {
            book.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
