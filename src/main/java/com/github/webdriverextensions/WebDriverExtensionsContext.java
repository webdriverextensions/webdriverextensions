package com.github.webdriverextensions;

import com.github.webdriverextensions.internal.WebDriverExtensionException;
import org.openqa.selenium.WebDriver;

public class WebDriverExtensionsContext {

    private WebDriverExtensionsContext() {}

    private static InheritableThreadLocal<WebDriver> threadLocalDriver = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            throw new WebDriverExtensionException("Driver in WebDriverExtensionsContext is not set. Please set the driver with WebDriverExtensionsContext.setDriver(...) method before using the WebDriverExtensions framework. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return threadLocalDriver.get();
    }

    public static void removeDriver() {
        threadLocalDriver.remove();
    }

    public static void setDriver(WebDriver driver) {
        threadLocalDriver.set(driver);
    }

}
