package com.oleg.blockedtest;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.parse.ReadFromExcel;
import com.wiley.basetests.SeleniumBaseTest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Date;

/**
 * Finds the necessary locator and inserts a text value into it.
 */

public class BlockedTest extends SeleniumBaseTest {

    static final String FILE_NAME_AND_DIRECTORY = "E:/test/test.xls";

    @Test
    public void testCheckUrls() {

        ReadFromExcel readFromExcel = new ReadFromExcel();
        BlockedInRussiaPage page = get(BlockedInRussiaPage.class, "http://isitblockedinrussia.com");

        for (int i = 0; i < readFromExcel.read(FILE_NAME_AND_DIRECTORY).size(); i++) {
            String url = readFromExcel.read(FILE_NAME_AND_DIRECTORY).get(i);
            page.inputUrl(url);
            BlockedInRussiaPage blockedInRussiaPage = new BlockedInRussiaPage();

            if (blockedInRussiaPage.checkAdressIsBlockt()) {

                FileInputStream inputStream = null;
                inputStream = getFileInputStream(inputStream);
                Workbook book = null;
                book = getSheets((FileInputStream) inputStream, (Workbook) book);

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
                assert book != null;
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
