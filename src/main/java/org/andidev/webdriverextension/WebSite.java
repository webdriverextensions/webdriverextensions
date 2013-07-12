package org.andidev.webdriverextension;

import org.andidev.webdriverextension.internal.Openable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class WebSite implements Openable {

    private WebDriver internalDriver;

    public void initElements() {
        internalDriver = ThreadDriver.getDriver();
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(ThreadDriver.getDriver()), this);
    }

    public void initElements(WebDriver driver) {
        this.internalDriver = driver;
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver), this);
    }

    public void initElements(WebDriver driver, FieldDecorator decorator) {
        this.internalDriver = driver;
        PageFactory.initElements(decorator, this);
    }

    @Override
    public abstract String getUrl();

    @Override
    public void open() {
        internalDriver.get(getUrl());
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
    public abstract void assertIsOpen() throws AssertionError;

    @Override
    public void assertIsNotOpen() throws AssertionError {
        if (isNotOpen()) {
            throw new AssertionError(this.getClass().getSimpleName() + " is open when it shouldn't!");
        }
    }

    public void open(String url) {
        internalDriver.get(url);
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

    public void assertIsOpen(Openable openable) throws AssertionError {
        openable.assertIsOpen();
    }

    public void assertIsNotOpen(Openable openable) throws AssertionError {
        openable.assertIsNotOpen();
    }
}