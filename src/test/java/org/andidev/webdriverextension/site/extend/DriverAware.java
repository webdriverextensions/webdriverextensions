package org.andidev.webdriverextension.site.extend;

import org.openqa.selenium.WebDriver;

public abstract class DriverAware {

    protected WebDriver driver;

    public DriverAware() {
    }

    public DriverAware(WebDriver driver) {
        setDriver(driver);
    }


    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}
