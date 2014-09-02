package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.WebDriverExtensionsContext;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;

public class ExperimentalBot {

    /* Browser */
    public static String browser() {
        return ((HasCapabilities) WebDriverExtensionsContext.getDriver()).getCapabilities().getBrowserName();
    }

    public static boolean browserIs(String browserName) {
        return StringUtils.equalsIgnoreCase(browser(), browserName);
    }

    public static boolean browserIsNot(String browserName) {
        return !browserIs(browserName);
    }

    public static boolean browserIsAndroid() {
        return browserIs(BrowserType.ANDROID);
    }

    public static boolean browserIsNotAndroid() {
        return !browserIsAndroid();
    }

    public static boolean browserIsChrome() {
        return browserIs(BrowserType.CHROME) || browserIs(BrowserType.GOOGLECHROME);
    }

    public static boolean browserIsNotChrome() {
        return !browserIsChrome();
    }

    public static boolean browserIsFirefox() {
        return browserIs(BrowserType.FIREFOX);
    }

    public static boolean browserIsNotFirefox() {
        return !browserIsFirefox();
    }

    public static boolean browserIsHtmlUnit() {
        return browserIs(BrowserType.HTMLUNIT);
    }

    public static boolean browserIsNotHtmlUnit() {
        return !browserIsHtmlUnit();
    }

    public static boolean browserIsIPad() {
        return browserIs(BrowserType.IPAD);
    }

    public static boolean browserIsNotIPad() {
        return !browserIsIPad();
    }

    public static boolean browserIsIPhone() {
        return browserIs(BrowserType.IPHONE);
    }

    public static boolean browserIsNotIPhone() {
        return !browserIsIPhone();
    }

    public static boolean browserIsInternetExplorer() {
        return browserIs(BrowserType.IE) || browserIs(BrowserType.IEXPLORE);
    }

    public static boolean browserIsNotInternetExplorer() {
        return !browserIsInternetExplorer();
    }

    public static boolean browserIsOpera() {
        return browserIs(BrowserType.OPERA);
    }

    public static boolean browserIsNotOpera() {
        return !browserIsOpera();
    }

    public static boolean browserIsPhantomJS() {
        return browserIs(BrowserType.PHANTOMJS);
    }

    public static boolean browserIsNotPhantomJS() {
        return !browserIsPhantomJS();
    }

    public static boolean browserIsSafari() {
        return browserIs(BrowserType.SAFARI);
    }

    public static boolean browserIsNotSafari() {
        return !browserIsSafari();
    }

    /* Version */
    public static String version() {
        return ((HasCapabilities) WebDriverExtensionsContext.getDriver()).getCapabilities().getVersion();
    }

    public static boolean versionIs(String version) {
        return StringUtils.equalsIgnoreCase(version(), version);
    }

    public static boolean versionIsNot(String version) {
        return !versionIs(version);
    }

    /* Platform */
    public static Platform platform() {
        return ((HasCapabilities) WebDriverExtensionsContext.getDriver()).getCapabilities().getPlatform();
    }

    public static boolean platformIs(Platform platform) {
        return platform().is(platform);
    }

    public static boolean platformIsNot(Platform platform) {
        return !platformIs(platform);
    }

    public static boolean platformIsAndroid() {
        return platformIs(Platform.ANDROID);
    }

    public static boolean platformIsNotAndroid() {
        return !platformIsAndroid();
    }

    public static boolean platformIsLinux() {
        return platformIs(Platform.LINUX);
    }

    public static boolean platformIsNotLinux() {
        return !platformIsLinux();
    }

    public static boolean platformIsMac() {
        return platformIs(Platform.MAC);
    }

    public static boolean platformIsNotMac() {
        return !platformIsMac();
    }

    public static boolean platformIsUnix() {
        return platformIs(Platform.UNIX);
    }

    public static boolean platformIsNotUnix() {
        return !platformIsUnix();
    }

    public static boolean platformIsWindows() {
        return platformIs(Platform.WINDOWS);
    }

    public static boolean platformIsNotWindows() {
        return !platformIsWindows();
    }

    public static boolean platformIsWindows8() {
        return platformIs(Platform.WIN8);
    }

    public static boolean platformIsNotWindows8() {
        return !platformIsWindows8();
    }

    public static boolean platformIsWindowsVista() {
        return platformIs(Platform.VISTA);
    }

    public static boolean platformIsNotWindowsVista() {
        return !platformIsWindowsVista();
    }

    public static boolean platformIsWindowsXP() {
        return platformIs(Platform.XP);
    }

    public static boolean platformIsNotWindowsXP() {
        return !platformIsWindowsXP();
    }

    /* Execute Javascript */
    public static Object executeJavascript(String script, Object... arguments) {
        return ((JavascriptExecutor) WebDriverExtensionsContext.getDriver()).executeScript(script, arguments);
    }

    public static Object executeJavascriptAsynchronously(String script, Object... arguments) {
        return ((JavascriptExecutor) WebDriverExtensionsContext.getDriver()).executeAsyncScript(script, arguments);
    }

    /* Scrolling */
    public static Object scrollTo(WebElement webElement) {
        return executeJavascript("arguments[0].scrollIntoView(true);", webElement);
    }
}
