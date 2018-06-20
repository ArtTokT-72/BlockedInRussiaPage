package com.oleg.blockedtest;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.parse.Parser;
import com.wiley.basetests.SeleniumBaseTest;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

/**
 * Finds the necessary locator and inserts a text value into it.
 */

public class BlockedTest extends SeleniumBaseTest {

    @Test
    public void testCheckUrls() throws InterruptedException {

        Parser parser = new Parser();
        BlockedInRussiaPage page = get(BlockedInRussiaPage.class, "http://isitblockedinrussia.com");

        for (int i = 0; i < parser.parse().size(); i++) {
            String url = parser.parse().get(i);
            page.inputUrl(url);
            BlockedInRussiaPage blockedInRussiaPage = new BlockedInRussiaPage();

            if (blockedInRussiaPage.checkAdressIsBlockt() == true) {
                System.out.println("доступен");
            } else {
                System.out.println("не доступен");
            }

            sleep(10000);
        }
    }
}
