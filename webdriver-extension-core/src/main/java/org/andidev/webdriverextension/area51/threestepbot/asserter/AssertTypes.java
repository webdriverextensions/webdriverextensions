package org.andidev.webdriverextension.area51.threestepbot.asserter;

import org.andidev.webdriverextension.bot.festbot.asserttypes.AssertStringTypes;
import org.andidev.webdriverextension.WebElement;
import org.openqa.selenium.WebDriver;

public class AssertTypes {

    private final WebDriver driver;
    private final WebElement[] webElements;

    public AssertTypes(WebDriver driver, WebElement... webElements) {
        this.driver = driver;
        this.webElements = webElements;
    }

//    public AssertStringTypes text() {
//        return new AssertStringTypes(webElement.getText());
//    }
}
