package org.andidev.webdriverextension.area51;

import java.net.MalformedURLException;
import java.net.URL;
import org.andidev.webdriverextension.exceptions.WebDriverExtensionException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Environment {

    
//    ANDROID,
//    CHROME,
//    FIREFOX,
//    HTML_UNIT,
//    INTERNET_EXPLORER,
//    IPAD,
//    IPHONE,
//    OPERA,
//    PHANTOMJS,
//    SAFARI;
//
//    public static Browser valueOfBrowserName(String browserName) {
//        if ("htmlunit".equals(browserName)) {
//            return HTML_UNIT;
//        } else if ("internet explorer".equals(browserName)) {
//            return INTERNET_EXPLORER;
//        } else {
//            return valueOf(browserName.toLowerCase());
//        }
//    }
//
//    public String getBrowserName() {
//        switch (this) {
//            case HTML_UNIT:
//                return "htmlunit";
//            case INTERNET_EXPLORER:
//                return "internet explorer";
//            default:
//                return this.toString().toLowerCase();
//        }
//    }
    private static URL remoteAddress;
    private static ThreadLocal<String> browserNameTl = new ThreadLocal<String>();
    private static ThreadLocal<String> browserVersionTl = new ThreadLocal<String>();
    private static ThreadLocal<Platform> platformTl = new ThreadLocal<Platform>();
    private static ThreadLocal<DesiredCapabilities> desiredCapabilitiesTl = new ThreadLocal<DesiredCapabilities>();
    private static ThreadLocal<WebDriver> driverTl = new ThreadLocal<WebDriver>();

    public static URL getRemoteAddress() {
        return remoteAddress;
    }

    public static void setRemoteAddress(String remoteAddress) throws MalformedURLException {
        Environment.remoteAddress = new URL(remoteAddress);
    }

    public static void setRemoteAddress(URL remoteAddress) {
        Environment.remoteAddress = remoteAddress;
    }

    public static String getBrowserName() {
        return browserNameTl.get();
    }

    public static String getBrowserVersion() {
        return browserVersionTl.get();
    }

    public static Platform getPlatform() {
        return platformTl.get();
    }

    public static WebDriver getDriver() {
        if (driverTl.get() == null) {
            throw new WebDriverExtensionException("WebDriver in Browser is not set. Please set the driver with Browser.setDriver(driver) before using it. Note that the driver will be thread safe since ThreadLocal is used so don't worry about thread safety.");
        }
        return driverTl.get();
    }

    public static void setDriver(String name) {
        setDriver(name, "", Platform.ANY);
    }

    public static void setDriver(String browserName, String borwserVersion) {
        setDriver(browserName, borwserVersion, Platform.ANY);
    }

    public static void setDriver(String browserName, String borwserVersion, Platform platform) {
        browserNameTl.set("".equals(browserName) ? null : browserName);
        browserVersionTl.set("".equals(borwserVersion) ? null : borwserVersion);
        platformTl.set(platform);
        desiredCapabilitiesTl.set(new DesiredCapabilities(browserName, borwserVersion, platform));
        if (remoteAddress != null) {
            driverTl.set(new RemoteWebDriver(remoteAddress, desiredCapabilitiesTl.get()));
        } else {
            throw new NotImplementedException();
        }
    }

    public static void setDriver(WebDriver driver) {
        setDriver(null, null, null, driver);
    }

    public static void setDriver(String browserName, WebDriver driver) {
        setDriver(browserName, null, null, driver);
    }

    public static void setDriver(String browserName, String borwserVersion, WebDriver driver) {
        setDriver(browserName, borwserVersion, null, driver);
    }

    public static void setDriver(String browserName, String borwserVersion, Platform platform, WebDriver driver) {
        browserNameTl.set(browserName);
        browserVersionTl.set(borwserVersion);
        platformTl.set(platform);
        driverTl.set(driver);
    }

    public static void setDriver(DesiredCapabilities desiredCapabilities) {
        browserNameTl.set(desiredCapabilities.getBrowserName());
        browserVersionTl.set(desiredCapabilities.getVersion());
        platformTl.set(desiredCapabilities.getPlatform());
        desiredCapabilitiesTl.set(desiredCapabilities);
        if (remoteAddress != null) {
            driverTl.set(new RemoteWebDriver(remoteAddress, desiredCapabilitiesTl.get()));
        } else {
            throw new NotImplementedException();
        }
    }
}
