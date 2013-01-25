package org.andidev.webdriverdesignproposalopenableredesign;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class PageObject<P extends PageObject> implements OpenableI<P> {

    private WebDriver driver;

    public PageObject(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getUrl() {
        return null;
    }
    
}
