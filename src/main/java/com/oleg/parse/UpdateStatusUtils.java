package com.oleg.parse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;

public class UpdateStatusUtils {

    public static void writeStatusUpToExcel(String file) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.getSheet("Sheet0");
        Row row = sheet.createRow(0);

        Cell status = row.createCell(1);
        status.setCellValue("Доступен");
        try {
            book.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeStatusOffToExcel(String file) {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.getSheet("Sheet0");
        Row row = sheet.createRow(0);

        Cell status = row.createCell(1);
        status.setCellValue(" Не доступен");
        try {
            book.write(new FileOutputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
