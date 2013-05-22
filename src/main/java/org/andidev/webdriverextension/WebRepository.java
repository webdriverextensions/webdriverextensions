package org.andidev.webdriverextension;

import org.andidev.webdriverextension.internal.WebDriverExtensionFieldDecorator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.FieldDecorator;

public abstract class WebRepository {

    public void initElements() {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(ThreadDriver.getDriver(), ThreadDriver.getDriver()), this);
    }

    public void initElements(WebDriver driver) {
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver, driver), this);
    }

    public void initElements(FieldDecorator decorator) {
        PageFactory.initElements(decorator, this);
    }
}