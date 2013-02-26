package org.andidev.webdriverextension.twostepbot2;

import org.andidev.webdriverextension.twostepbot2.asserters.AssertTypes;
import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.twostepbot2.asserters.IsTypes;
import org.openqa.selenium.WebDriver;

public class Bot {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void click(WebElement webElement) {
        webElement.click();
    }

    public IsTypes is(WebElement... webElements) {
        return new IsTypes(driver, webElements);
    }

    public AssertTypes assertThat(WebElement... webElements) {
        return new AssertTypes(driver, webElements);
    }


}
