package org.andidev.webdriverextension.bot.festbot.readtypes;

import java.util.List;
import org.andidev.webdriverextension.bot.BotUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReadTypes {

    private final WebDriver driver;
    private final WebElement webElement;

    public ReadTypes(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.webElement = webElement;
    }

    public String text() {
        return BotUtils.read(webElement);
    }

    public Double number() {
        return BotUtils.readNumber(webElement);
    }

    public List<String> options() {
        return BotUtils.readOptions(webElement);
    }

    public String url() {
        return BotUtils.readUrl(driver);
    }

    public String tagName() {
        return BotUtils.readTagName(webElement);
    }

    public String attribute(String name) {
        return BotUtils.readAttribute(name, webElement);
    }

    public String id() {
        return BotUtils.readId(webElement);
    }

    public String name() {
        return BotUtils.readName(webElement);
    }

    public List<String> classes() {
        return BotUtils.readClasses(webElement);
    }

    public String value() {
        return BotUtils.readValue(webElement);
    }

    public String href() {
        return BotUtils.readHref(webElement);
    }
}
