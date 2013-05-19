package org.andidev.webdriverextension.internal;

import java.net.URL;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CurrentBrowser {

    private static ThreadLocal<WebDriver> driverTl = new ThreadLocal<WebDriver>();

    public static String getBrowserName() {
        return ((RemoteWebDriver) getDriver()).getCapabilities().getBrowserName();
    }

    public static String getBrowserVersion() {
        return ((RemoteWebDriver) getDriver()).getCapabilities().getVersion();
    }

    public static Platform getPlatform() {
        return ((RemoteWebDriver) getDriver()).getCapabilities().getPlatform();
    }

    public static WebDriver getDriver() {
        if (driverTl.get() == null) {
            throw new WebDriverExtensionException("Driver in CurrentBrowser is not set. Please set the driver with any CurrentBrowser.setDriver(...) method before using it. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return driverTl.get();
    }

    public static void setDriver(WebDriver driver) {
        driverTl.set(driver);
    }

    public static void setDriver(DesiredCapabilities desiredCapabilities) {
        driverTl.set(new RemoteWebDriver(desiredCapabilities));
    }

    public static void setDriver(URL remoteAddress, DesiredCapabilities desiredCapabilities) {
        driverTl.set(new RemoteWebDriver(remoteAddress, desiredCapabilities));
    }

    public static void setDriver(String browserName) {
        driverTl.set(new RemoteWebDriver(new DesiredCapabilities(browserName, "", Platform.ANY)));
    }

    public static void setDriver(URL remoteAddress, String browserName) {
        driverTl.set(new RemoteWebDriver(remoteAddress, new DesiredCapabilities(browserName, "", Platform.ANY)));
    }

    public static void setDriver(String browserName, String browserVersion) {
        driverTl.set(new RemoteWebDriver(new DesiredCapabilities(browserName, browserVersion, Platform.ANY)));
    }

    public static void setDriver(URL remoteAddress, String browserName, String browserVersion) {
        driverTl.set(new RemoteWebDriver(remoteAddress, new DesiredCapabilities(browserName, browserVersion, Platform.ANY)));
    }

    public static void setDriver(String browserName, String browserVersion, Platform platform) {
        driverTl.set(new RemoteWebDriver(new DesiredCapabilities(browserName, browserVersion, platform)));
    }

    public static void setDriver(URL remoteAddress, String browserName, String browserVersion, Platform platform) {
        driverTl.set(new RemoteWebDriver(remoteAddress, new DesiredCapabilities(browserName, browserVersion, platform)));
    }
}
