package com.github.webdriverextensions;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.github.webdriverextensions.events.EventBindingDriver;
import com.github.webdriverextensions.internal.WebDriverExtensionException;
import com.github.webdriverextensions.reports.Reporter;

public class WebDriverExtensionsContext {

    private static InheritableThreadLocal<WebDriver> threadLocalDriver = new InheritableThreadLocal<>();
    private static InheritableThreadLocal<Reporter> threadLocalReporter = new InheritableThreadLocal<>();
    static WebDriver defaultDriver;

    public static WebDriver getDriver() {
        if (threadLocalDriver.get() == null) {
            throw new WebDriverExtensionException(
                    "Driver in WebDriverExtensionsContext is not set. Please set the driver with WebDriverExtensionsContext.setDriver(...) method before using the WebDriverExtensions framework. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return threadLocalDriver.get();
    }

    public static void removeDriver() {
        threadLocalDriver.remove();
    }

    public static void setDriver(WebDriver driver) {
        defaultDriver = driver;
        driver = EventBindingDriver.attachEventDriver(driver);
        threadLocalDriver.set(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public static Reporter getReporter() {
        if (threadLocalReporter.get() == null) {
            throw new WebDriverExtensionException(
                    "ExtentReports in WebDriverExtensionsContext is not set. Please set the reporter with WebDriverExtensionsContext.setReporter(...) method before using the WebDriverExtensions framework. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return threadLocalReporter.get();
    }

    public static void removeReporter() {
        getReporter().flushReport();
        // threadLocalReporter.remove();
    }

    public static void setReporter(Reporter reporter) {
        threadLocalReporter.set(reporter);
    }

    public static WebDriver getDriverDefault() {
        if (defaultDriver == null) {
            throw new WebDriverExtensionException(
                    "Driver in WebDriverExtensionsContext is not set. Please set the driver with WebDriverExtensionsContext.setDriver(...) method before using the WebDriverExtensions framework. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return defaultDriver;
    }
}
