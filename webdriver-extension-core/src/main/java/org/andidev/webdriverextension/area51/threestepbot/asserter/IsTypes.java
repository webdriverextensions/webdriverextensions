package org.andidev.webdriverextension.area51.threestepbot.asserter;

import org.andidev.webdriverextension.bot.festbot.asserttypes.AssertStringTypes;
import org.andidev.webdriverextension.WebElement;
import org.openqa.selenium.WebDriver;

public class IsTypes {

    private final WebDriver driver;
    private final WebElement[] webElements;

    public IsTypes(WebDriver driver, WebElement... webElements) {
        this.driver = driver;
        this.webElements = webElements;
    }

//    public AssertStringTypes text() {
//        return new StringIsOptions(webElements.getText());
//    }
}
