package org.andidev.webdriverextension.internal.area51.festbot;

import lombok.Delegate;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AssertTypes {

    private final WebDriver driver;
    private final WebElement webElement;
    @Delegate
    private final AssertWebElementTypes assertWebElementTypes;
    @Delegate
    private final AssertClassTypes assertClassTypes;
    @Delegate
    private final AssertOptionsTypes assertOptionsTypes;

    public AssertTypes(WebDriver driver, WebElement webElement) {
        this.driver = driver;
        this.webElement = webElement;
        assertWebElementTypes = new AssertWebElementTypes(webElement);
        assertClassTypes = new AssertClassTypes(webElement.getAttribute("class"));
        assertOptionsTypes = new AssertOptionsTypes(webElement);
    }

    public AssertStringTypes tagName() {
        return new AssertStringTypes(webElement.getTagName());
    }

    public AssertStringTypes attribute(String name) {
        return new AssertStringTypes(webElement.getAttribute(name));
    }

    public AssertStringTypes id() {
        return new AssertStringTypes(webElement.getAttribute("id"));
    }

    public AssertStringTypes name() {
        return new AssertStringTypes(webElement.getAttribute("name"));
    }

    public AssertStringTypes value() {
        return new AssertStringTypes(webElement.getAttribute("value"));
    }

    public AssertStringTypes href() {
        return new AssertStringTypes(webElement.getAttribute("href"));
    }

    public AssertStringTypes text() {
        return new AssertStringTypes(webElement.getText());
    }

    public AssertDoubleTypes number() {
        return new AssertDoubleTypes(webElement.getText());
    }
}