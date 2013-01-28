package org.andidev.webdriverextension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class WebPage implements Openable {

    private WebDriver driver;

    public WebPage(WebDriver driver) {
        // Set WebDriver
        this.driver = driver;

        // Init WebElements
        PageFactory.initElements(new DefaultWebElementFieldDecorator(driver), this);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        // Set WebDriver
        this.driver = driver;

        // Init WebElements
        PageFactory.initElements(new DefaultWebElementFieldDecorator(driver), this);
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