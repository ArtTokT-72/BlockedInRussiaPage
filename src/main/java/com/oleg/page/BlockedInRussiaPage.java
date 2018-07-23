package com.oleg.page;

import com.wiley.elements.TeasyElement;
import com.wiley.page.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

/**
 * Finds the necessary locator and inserts a text value into it.
 */

public class BlockedInRussiaPage extends BasePage {

    @Step
    public void inputUrl(String url) {
        TeasyElement input = element(By.cssSelector("input[class='uk-input uk-form-large']"));
        input.clear();
        input.sendKeys(url);
    }

    @Step
    public boolean checkAdressIsBlockt() {
        return !elements(By.cssSelector("h2[class='uk-h2 uk-text-success']")).isEmpty();
    }
}
