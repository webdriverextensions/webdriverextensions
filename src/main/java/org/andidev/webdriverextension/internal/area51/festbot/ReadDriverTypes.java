package org.andidev.webdriverextension.internal.area51.festbot;

import org.andidev.webdriverextension.internal.BotUtils;
import org.openqa.selenium.WebDriver;

public class ReadDriverTypes {

    private final WebDriver driver;

    public ReadDriverTypes(WebDriver driver) {
        this.driver = driver;
    }

    public String url() {
        return BotUtils.readUrl(driver);
    }

}
