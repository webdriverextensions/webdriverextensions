package org.andidev.webdriverextension;

import org.openqa.selenium.WebDriver;

public abstract class WebSite implements Openable {

    private WebDriver driver;

    public WebSite() {
    }
    public WebSite(WebDriver driver) {
        setDriver(driver);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebSite setDriver(WebDriver driver) {
        this.driver = driver;
        return this;
    }

    public abstract String getUrl();

    @Override
    public void open() {
        driver.get(getUrl());
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