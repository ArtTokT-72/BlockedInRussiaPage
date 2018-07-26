package com.oleg.blockedtest;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.utils.ExcelUtils;
import com.oleg.read.ReadFromExcel;
import com.wiley.basetests.SeleniumBaseTest;
import org.testng.annotations.Test;

/**
 * Finds the necessary locator and inserts a text value into it.
 */

public class BlockedTest extends SeleniumBaseTest {

    @Test
    public void testCheckUrls() {

        BlockedInRussiaPage page = get(BlockedInRussiaPage.class, "http://isitblockedinrussia.com");
        ExcelUtils excelUtils = new ExcelUtils();
        ReadFromExcel readFromExcel = new ReadFromExcel();
        excelUtils.getCheckUrls(readFromExcel, page);
    }
}
