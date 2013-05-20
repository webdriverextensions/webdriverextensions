package org.andidev.webdriverextension;

import org.andidev.webdriverextension.internal.ThreadDriver;
import org.andidev.webdriverextension.internal.WebDriverExtensionFieldDecorator;
import org.andidev.webdriverextension.internal.Openable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class WebPage<S> implements Openable {

    public S site;

    public void initElements() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(ThreadDriver.getDriver(), ThreadDriver.getDriver()), this);
    }

    public void initElements(WebDriver driver) {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver, driver), this);
    }

    public void initElements(FieldDecorator decorator) {
        PageFactory.initElements(decorator, this);
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