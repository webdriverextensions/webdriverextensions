package org.andidev.webdriverextension.bot.fest.readtypes;

import org.andidev.webdriverextension.bot.BotUtils;
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
