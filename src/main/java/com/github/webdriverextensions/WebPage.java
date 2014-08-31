package com.github.webdriverextensions;

import com.github.webdriverextensions.internal.Openable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class WebPage implements Openable {

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
        } catch (AssertionError e) {
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

    public void open(String url) {
        Bot.open(url);
    }

    public void open(Openable openable) {
        Bot.open(openable);
    }

    public boolean isOpen(Openable openable) {
        return Bot.isOpen(openable);
    }

    public boolean isNotOpen(Openable openable) {
        return Bot.isNotOpen(openable);
    }

    public void assertIsOpen(Openable openable) throws AssertionError {
        Bot.assertIsOpen(openable);
    }

    public void assertIsNotOpen(Openable openable) throws AssertionError {
        Bot.assertIsNotOpen(openable);
    }
}