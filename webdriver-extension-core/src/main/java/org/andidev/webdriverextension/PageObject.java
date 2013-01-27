package org.andidev.webdriverextension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class PageObject implements Openable {

    private WebDriver driver;

    public PageObject(WebDriver driver) {
        // Set WebDriver
        this.driver = driver;

        // Init HtmlTags
        PageFactory.initElements(new DefaultHtmlTagFieldDecorator(driver), this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        // Set WebDriver
        this.driver = driver;

        // Init HtmlTags
        PageFactory.initElements(new DefaultHtmlTagFieldDecorator(driver), this);
    }

    public String getUrl() {
        return null;
    }

    public boolean isOpen() {
        try {
            assertIsOpen();
            return true;
        } catch (Error e) {
            return false;
        }
    }
}
