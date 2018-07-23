package com.oleg.parse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * class allows you to do the parsing of the file type xls.
 * The data is placed in the Arraylist collection
 */

public class Parser {

    public List<String> parse() {

        InputStream in;
        List<String> result = new ArrayList<String>();
        HSSFWorkbook wb = null;
        try {
            in = new FileInputStream("E:/test/test.xls");
            wb = new HSSFWorkbook(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert wb != null;
        Sheet sheet = wb.getSheetAt(0);

        int rowNumber = sheet.getLastRowNum() + 1;
        for (int i = 0; i < rowNumber; i++) {

            Row row = sheet.getRow(i);

            Cell cell = row.getCell(0);
            int cellType = cell.getCellType();

            switch (cellType) {
                case Cell.CELL_TYPE_STRING:
                    result.add(cell.getStringCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    result.add(Double.toString(cell.getNumericCellValue()));
                    break;

                case Cell.CELL_TYPE_FORMULA:
                    result.add(Double.toString(cell.getNumericCellValue()));
                    break;
                default:
                    result.add("|");
                    break;
            }
        }

        return result;
    }
}

