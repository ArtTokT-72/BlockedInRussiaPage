package com.oleg.blockedtest;

import com.oleg.page.BlockedInRussiaPage;
import com.oleg.parse.Parser;
import com.wiley.basetests.SeleniumBaseTest;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import static com.sun.javafx.fxml.expression.Expression.get;
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
            sleep(10000);
        }

    }
}
