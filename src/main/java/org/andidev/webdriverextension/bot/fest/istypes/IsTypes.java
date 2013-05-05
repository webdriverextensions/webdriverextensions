package org.andidev.webdriverextension.bot.fest.istypes;

import lombok.Delegate;
import org.andidev.webdriverextension.bot.BotUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class IsTypes {

    private final WebDriver driver;
    private final WebElement webElement;
    @Delegate
    private final IsStringTypes isTextTypes;
    @Delegate
    private final IsClassTypes isClassTypes;
    @Delegate
    private final IsOptionsTypes isOptionsTypes;

    public IsTypes(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.webElement = webElement;
        isTextTypes = new IsStringTypes(webElement.getText());
        isClassTypes = new IsClassTypes(webElement.getAttribute("class"));
        isOptionsTypes = new IsOptionsTypes(webElement);
    }

    public boolean text(String text) {
        return BotUtils.is(text, webElement.getText());
    }

    public IsStringTypes tagName() {
        return new IsStringTypes(webElement.getTagName());
    }

    public IsStringTypes attribute(String name) {
        return new IsStringTypes(webElement.getAttribute(name));
    }

    public IsStringTypes id() {
        return new IsStringTypes(webElement.getAttribute("id"));
    }

    public IsStringTypes name() {
        return new IsStringTypes(webElement.getAttribute("name"));
    }

    public IsStringTypes value() {
        return new IsStringTypes(webElement.getAttribute("value"));
    }

    public IsStringTypes href() {
        return new IsStringTypes(webElement.getAttribute("href"));
    }

    public IsStringTypes text() {
        return new IsStringTypes(webElement.getText());
    }

    public IsDoubleTypes number() {
        return new IsDoubleTypes(NumberUtils.createDouble(webElement.getText()));
    }
}
