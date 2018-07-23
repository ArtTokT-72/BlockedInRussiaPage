package com.oleg.blockedtest;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.parse.ExcelUtil;
import com.oleg.parse.Parser;
import com.wiley.basetests.SeleniumBaseTest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Date;

import static java.lang.Thread.sleep;

/**
 * Finds the necessary locator and inserts a text value into it.
 */

public class BlockedTest extends SeleniumBaseTest {

    @Test
    public void testCheckUrls() throws Exception {

        Parser parser = new Parser();
        BlockedInRussiaPage page = get(BlockedInRussiaPage.class, "http://isitblockedinrussia.com");

        for (int i = 0; i < parser.parse().size(); i++) {
            String url = parser.parse().get(i);
            page.inputUrl(url);
            BlockedInRussiaPage blockedInRussiaPage = new BlockedInRussiaPage();

            if (blockedInRussiaPage.checkAdressIsBlockt()) {
                String fileName = "E:/test/test.xls";

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
                Row row = sheet.getRow(i);
                Cell status = row.createCell(1);
                status.setCellValue("address available");

                Cell data = row.createCell(2);
                Date date = new Date();
                data.setCellValue(date.toString());

                try {
                    book.write(new FileOutputStream("E:/test/test.xls"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
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

                Row row = sheet.getRow(i);

                Cell status = row.createCell(1);
                status.setCellValue("address not available");

                Cell data = row.createCell(2);
                Date date = new Date();
                data.setCellValue(date.toString());
                try {
                    book.write(new FileOutputStream("E:/test/test.xls"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
