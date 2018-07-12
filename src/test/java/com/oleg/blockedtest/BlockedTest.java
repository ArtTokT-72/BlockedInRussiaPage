package com.oleg.blockedtest;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.parse.UpdateStatusUtils;
import com.oleg.parse.Parser;
import com.wiley.basetests.SeleniumBaseTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static java.lang.Thread.sleep;

/**
 * Finds the necessary locator and inserts a text value into it.
 */

public class BlockedTest extends SeleniumBaseTest {

    @Test
    public void testCheckUrls() {

        Parser parser = new Parser();
        BlockedInRussiaPage page = get(BlockedInRussiaPage.class, "http://isitblockedinrussia.com");

        for (int i = 0; i < parser.parse().size(); i++) {
            String url = parser.parse().get(i);
            page.inputUrl(url);
            BlockedInRussiaPage blockedInRussiaPage = new BlockedInRussiaPage();

            if (blockedInRussiaPage.checkAdressIsBlockt()) {
                String fileName = "E:/test/test.xls";
                UpdateStatusUtils.writeStatusUpToExcel(fileName);

            } else {
                String fileName = "E:/test/test.xls";
                UpdateStatusUtils.writeStatusOffToExcel(fileName);

            }
        }
    }
}
