package org.andidev.webdriverextension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class PageObject<P extends PageObject<P>> extends LoadableComponent<P> {

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
    
    @Override
    public abstract void isLoaded() throws Error;

}
