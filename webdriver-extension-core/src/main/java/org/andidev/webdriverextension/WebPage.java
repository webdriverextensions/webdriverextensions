package org.andidev.webdriverextension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class WebPage<S> implements Openable {

    private WebDriver driver;
    public S site;

    public WebPage() {
    }

    public WebPage(WebDriver driver) {
        setDriver(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
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