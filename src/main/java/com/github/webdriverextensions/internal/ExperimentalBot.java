package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.ThreadDriver;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.android.AndroidDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class ExperimentalBot {

    /* Browser */
    public static String browser() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getBrowserName();
        }
        throw new WebDriverExtensionException("Sorry! browser() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }

    public static boolean browserIs(String browserName) {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(browserName);
        }
        throw new WebDriverExtensionException("Sorry! browserIs(String browserType) is only "
                + "implemented for RemoteWebDriver at the moment.");
    }

    public static boolean browserIsAndroid() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.ANDROID);
        } else if (ThreadDriver.getDriver() instanceof AndroidDriver) {
            return true;
        }
        return false;
    }

    public static boolean browserIsChrome() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.CHROME);
        } else if (ThreadDriver.getDriver() instanceof ChromeDriver) {
            return true;
        }
        return false;
    }

    public static boolean browserIsFirefox() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.FIREFOX);
        } else if (ThreadDriver.getDriver() instanceof FirefoxDriver) {
            return true;
        }
        return false;
    }

    public static boolean browserIsHtmlUnit() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.HTMLUNIT);
        } else if (ThreadDriver.getDriver() instanceof HtmlUnitDriver) {
            return true;
        }
        return false;
    }

    public static boolean browserIsIPad() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.IPAD);
        }
        throw new WebDriverExtensionException("Sorry! browserIsIPad() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }

    public static boolean browserIsIPhone() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.IPHONE);
        }
        throw new WebDriverExtensionException("Sorry! browserIsIPhone() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }

    public static boolean browserIsInternetExplorer() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.IE);
        } else if (ThreadDriver.getDriver() instanceof InternetExplorerDriver) {
            return true;
        }
        return false;
    }

    public static boolean browserIsOpera() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.OPERA);
        }
        throw new WebDriverExtensionException("Sorry! browserIsOpera() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }

    public static boolean browserIsPhantomJS() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.PHANTOMJS);
        }
        throw new WebDriverExtensionException("Sorry! browserIsPhantomJS() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }

    public static boolean browserIsSafari() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return isRemoteWebDriverBrowser(BrowserType.SAFARI);
        } else if (ThreadDriver.getDriver() instanceof SafariDriver) {
            return true;
        }
        return false;
    }

    /* Browser Utils */
    private static boolean isRemoteWebDriverBrowser(String browserName) {
        String currentBrowserName = ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getBrowserName();
        return StringUtils.equalsIgnoreCase(browserName, currentBrowserName);
    }

    /* Browser Version */
    public static String browserVersion() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getVersion();
        }
        throw new WebDriverExtensionException("Sorry! browserVersion() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }

    /* Platform */
    public static Platform platform() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getPlatform();
        }
        throw new WebDriverExtensionException("Sorry! platform() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }
    public static String platformAsString() {
        if (ThreadDriver.getDriver() instanceof RemoteWebDriver) {
            return ((RemoteWebDriver) ThreadDriver.getDriver()).getCapabilities().getPlatform().toString();
        }
        throw new WebDriverExtensionException("Sorry! platformAsString() is only "
                + "implemented for RemoteWebDriver at the moment.");
    }


    /* Execute JavaScript */
    public static Object executeJavaScript(String script, Object... args) {
        return ((JavascriptExecutor) ThreadDriver.getDriver()).executeScript(script, args);
    }

    public static Object executeJavaScriptAsynchronously(String script, Object... args) {
        return ((JavascriptExecutor) ThreadDriver.getDriver()).executeAsyncScript(script, args);
    }
}
