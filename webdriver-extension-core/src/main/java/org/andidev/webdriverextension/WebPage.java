package org.andidev.webdriverextension;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class WebPage<P extends WebPage<P>> extends LoadableComponent<P> {

    private WebDriver driver;

    public WebPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getUrl() {
        return "";
    }
    
    @Override
    public abstract void isLoaded() throws Error;

}
