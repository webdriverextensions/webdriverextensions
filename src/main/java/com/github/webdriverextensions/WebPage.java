package com.github.webdriverextensions;

import com.github.webdriverextensions.internal.Openable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class WebPage<S> implements Openable {

    public S site;

    public void initElements() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(WebDriverExtensionsContext.getDriver()), this);
    }

    public void initElements(WebDriver driver) {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver), this);
    }

    public void initElements(FieldDecorator decorator) {
        PageFactory.initElements(decorator, this);
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
            throw new AssertionError(this.getClass().getSimpleName() + " is open when it shouldn't");
        }
    }

    public static void open(String url) {
        WebDriverExtensionsContext.getDriver().get(url);
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