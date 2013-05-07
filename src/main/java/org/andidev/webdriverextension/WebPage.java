package org.andidev.webdriverextension;

import org.junit.Assert;
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
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver, driver), this);
    }

    @Override
    public String getUrl() {
        return null;
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