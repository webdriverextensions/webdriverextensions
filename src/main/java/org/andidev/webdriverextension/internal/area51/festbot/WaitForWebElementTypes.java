package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WaitForWebElementTypes {

    private final WebElement webElement;

    public WaitForWebElementTypes(WebElement webElement) {
        this.webElement = webElement;
    }

    public void toDisplay(WebDriver driver) {
        BotUtils.waitForElementToDisplay(webElement, driver);
    }
}
