package org.andidev.webdriverextension;

import org.junit.Assert;
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

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public abstract String getUrl();

    @Override
    public void open() {
        driver.get(getUrl());
    }

    @Override
    public boolean isOpen() {
        try {
            assertIsOpen();
            return true;
        } catch (Error e) {
            return false;
        }
    }

    @Override
    public boolean isNotOpen() {
        return !isOpen();
    }

    @Override
    public void assertIsNotOpen() throws Error {
        if (isNotOpen()) {
            Assert.fail(this.getClass().getSimpleName() + " is open when it shouldn't!");
        }
    }

    public void open(Openable openable) {
        openable.open();
    }

    public boolean isOpen(Openable openable) {
        return openable.isOpen();
    }

    public boolean isNotOpen(Openable openable) {
        return openable.isNotOpen();
    }

    public void assertIsOpen(Openable openable) throws Error {
        openable.assertIsOpen();
    }

    public void assertIsNotOpen(Openable openable) throws Error {
        openable.assertIsNotOpen();
    }
}