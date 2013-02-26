package org.andidev.webdriverextension.threestepbot.asserter;

import org.andidev.webdriverextension.twostepbot2.asserters.AssertStringTypes;
import org.andidev.webdriverextension.WebElement;
import org.openqa.selenium.WebDriver;

public class IsTypes {

    private final WebDriver driver;
    private final WebElement[] webElements;

    public IsTypes(WebDriver driver, WebElement... webElements) {
        this.driver = driver;
        this.webElements = webElements;
    }

    public AssertStringTypes text() {
        return new StringIsOptions(webElements.getText());
    }
}
