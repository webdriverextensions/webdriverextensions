package com.github.webdriverextensions;

import com.github.webdriverextensions.internal.Openable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class WebSite implements Openable {

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
    public abstract void open(Object... arguments);

    @Override
    public boolean isOpen(Object... arguments) {
        try {
            assertIsOpen(arguments);
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    @Override
    public boolean isNotOpen(Object... arguments) {
        return !isOpen(arguments);
    }

    @Override
    public abstract void assertIsOpen(Object... arguments) throws AssertionError;

    @Override
    public void assertIsNotOpen(Object... arguments) throws AssertionError {
        if (isNotOpen(arguments)) {
            throw new AssertionError(this.getClass().getSimpleName() + " is open when it shouldn't");
        }
    }



    // WORKAROUND: Make Bot methods visible that are otherwise hidden when using static import
    public void open(String url) {
        Bot.open(url);
    }

    public void open(Openable openable, Object... arguments) {
        Bot.open(openable, arguments);
    }

    public boolean isOpen(Openable openable, Object... arguments) {
        return Bot.isOpen(openable, arguments);
    }

    public boolean isNotOpen(Openable openable, Object... arguments) {
        return Bot.isNotOpen(openable, arguments);
    }

    public void assertIsOpen(Openable openable, Object... arguments) throws AssertionError {
        Bot.assertIsOpen(openable, arguments);
    }

    public void assertIsNotOpen(Openable openable, Object... arguments) throws AssertionError {
        Bot.assertIsNotOpen(openable, arguments);
    }
}