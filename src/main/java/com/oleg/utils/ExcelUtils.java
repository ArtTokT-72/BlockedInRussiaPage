package com.oleg.utils;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.read.ReadFromExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.*;
import java.util.Date;

public class ExcelUtils {

    static final String FILE_NAME_AND_DIRECTORY = "./src/test/resources/test.xls";

    public void getCheckUrls(ReadFromExcel readFromExcel, BlockedInRussiaPage page) {
        for (int i = 0; i < readFromExcel.read().size(); i++) {
            String url = readFromExcel.read().get(i);
            page.inputUrl(url);
            BlockedInRussiaPage blockedInRussiaPage = new BlockedInRussiaPage();

            if (blockedInRussiaPage.checkAddressIsBlocked()) {

                FileInputStream inputStream = null;
                inputStream = getFileInputStream(inputStream);
                Workbook book = null;
                book = getSheets(inputStream, book);

                if (book != null) {
                    Sheet sheet = book.getSheet("Sheet0");
                    Row row = sheet.getRow(i);
                    Cell status = row.createCell(1);
                    status.setCellValue("address available");
                    Cell data = row.createCell(2);
                    Date date = new Date();
                    data.setCellValue(date.toString());
                    getWorkbook(book);
                }

            } else {
                FileInputStream inputStream = null;
                inputStream = getFileInputStream(inputStream);
                Workbook book = null;
                book = getSheets(inputStream, book);
                if (book != null) {
                    Sheet sheet = book.getSheet("Sheet0");
                    Row row = sheet.getRow(i);
                    Cell status = row.createCell(1);
                    status.setCellValue("address not available");
                    Cell data = row.createCell(2);
                    Date date = new Date();
                    data.setCellValue(date.toString());
                    getWorkbook(book);
                }
            }
        }
    }

    private Workbook getSheets(FileInputStream inputStream, Workbook book) {
        try {
            if (inputStream != null)
                book = new HSSFWorkbook(inputStream);
        } catch (IOException e) {
        }
        return book;
    }

    private void getWorkbook(Workbook book) {
        try {
            book.write(new FileOutputStream(FILE_NAME_AND_DIRECTORY));
        } catch (IOException e) {

        }
    }

    private FileInputStream getFileInputStream(FileInputStream inputStream) {
        try {
            inputStream = new FileInputStream(new File(FILE_NAME_AND_DIRECTORY));
        } catch (FileNotFoundException e) {

        }
        return inputStream;
    }
}
