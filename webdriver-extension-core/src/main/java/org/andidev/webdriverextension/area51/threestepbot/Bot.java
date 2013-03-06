package org.andidev.webdriverextension.area51.threestepbot;

import org.andidev.webdriverextension.bot.festbot.asserttypes.AssertTypes;
import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.bot.festbot.asserttypes.IsTypes;
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

    public IsTypes is(WebElement webElement) {
        return new IsTypes(driver, webElement);
    }

//    public AssertTypes assertThat(WebElement... webElement) {
//        return new AssertTypes(driver, webElement);
//    }


}
