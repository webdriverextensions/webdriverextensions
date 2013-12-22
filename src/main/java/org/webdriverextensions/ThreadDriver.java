package org.webdriverextensions;

import java.net.URL;
import org.webdriverextensions.internal.WebDriverExtensionException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

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

    public static void setDriver(DesiredCapabilities desiredCapabilities) {
        threadDriver.set(new RemoteWebDriver(desiredCapabilities));
    }

    public static void setDriver(URL remoteAddress, DesiredCapabilities desiredCapabilities) {
        threadDriver.set(new RemoteWebDriver(remoteAddress, desiredCapabilities));
    }

    public static void setDriver(String browserName) {
        threadDriver.set(new RemoteWebDriver(new DesiredCapabilities(browserName, "", Platform.ANY)));
    }

    public static void setDriver(URL remoteAddress, String browserName) {
        threadDriver.set(new RemoteWebDriver(remoteAddress, new DesiredCapabilities(browserName, "", Platform.ANY)));
    }

    public static void setDriver(String browserName, String browserVersion) {
        threadDriver.set(new RemoteWebDriver(new DesiredCapabilities(browserName, browserVersion, Platform.ANY)));
    }

    public static void setDriver(URL remoteAddress, String browserName, String browserVersion) {
        threadDriver.set(new RemoteWebDriver(remoteAddress, new DesiredCapabilities(browserName, browserVersion, Platform.ANY)));
    }

    public static void setDriver(String browserName, String browserVersion, Platform platform) {
        threadDriver.set(new RemoteWebDriver(new DesiredCapabilities(browserName, browserVersion, platform)));
    }

    public static void setDriver(URL remoteAddress, String browserName, String browserVersion, Platform platform) {
        threadDriver.set(new RemoteWebDriver(remoteAddress, new DesiredCapabilities(browserName, browserVersion, platform)));
    }
}
