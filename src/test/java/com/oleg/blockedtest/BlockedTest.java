package com.oleg.blockedtest;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.utils.ExelUtils;
import com.oleg.read.ReadFromExcel;
import com.wiley.basetests.SeleniumBaseTest;
import org.testng.annotations.Test;

/**
 * Finds the necessary locator and inserts a text value into it.
 */

public class BlockedTest extends SeleniumBaseTest {

    @Test
    public void testCheckUrls() {

        ReadFromExcel readFromExcel = new ReadFromExcel();
        BlockedInRussiaPage page = get(BlockedInRussiaPage.class, "http://isitblockedinrussia.com");
        ExelUtils exelUtils = new ExelUtils();
        exelUtils.getCheckUrls(readFromExcel, page);
    }
}
