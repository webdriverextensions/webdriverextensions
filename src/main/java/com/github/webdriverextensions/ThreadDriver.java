package com.github.webdriverextensions;

import com.github.webdriverextensions.internal.WebDriverExtensionException;
import org.openqa.selenium.WebDriver;

public class ThreadDriver {

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        if (threadDriver.get() == null) {
            throw new WebDriverExtensionException("Driver in ThreadDriver is not set. Please set the driver with any ThreadDriver.setDriver(...) method before using it. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return threadDriver.get();
    }

    public static void removeDriver() {
        threadDriver.remove();
    }

    public static void setDriver(WebDriver driver) {
        threadDriver.set(driver);
    }

}
