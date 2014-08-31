package com.github.webdriverextensions.internal.junitrunner;

import com.github.webdriverextensions.internal.utils.FileUtils;
import com.github.webdriverextensions.internal.utils.OsUtils;
import com.github.webdriverextensions.internal.utils.PropertyUtils;
import com.github.webdriverextensions.junitrunner.annotations.DriverPaths;

public class DriverPathLoader {

    private static final String CHROME_DRIVER_PROPERTY_NAME = "webdriver.chrome.driver";
    private static final String IE_DRIVER_PROPERTY_NAME = "webdriver.ie.driver";
    private static final String INTERNET_EXPLORER_DRIVER_PROPERTY_NAME = "webdriver.internetexplorer.driver"; // Alternative property name that follows naming convention
    private static final String IE_DRIVER_USE64BIT_PROPERTY_NAME = "webdriver.ie.driver.use64Bit";
    private static final String INTERNET_EXPLORER_DRIVER_USE64BIT_PROPERTY_NAME = "webdriver.internetexplorer.driver.use64Bit";

    public static void loadDriverPaths(DriverPaths driverPaths) {
        loadChromeDriverPath(driverPaths != null ? driverPaths.chrome() : null);
        loadInternetExplorerDriverPath(driverPaths != null ? driverPaths.internetExplorer() : null);
        makeSureDriversAreExecutable();
    }

    private static void loadChromeDriverPath(String path) {
        PropertyUtils.setPropertyIfNotExists(CHROME_DRIVER_PROPERTY_NAME, path);
        PropertyUtils.setPropertyIfNotExists(CHROME_DRIVER_PROPERTY_NAME, getChromeDriverDefaultPath());
    }

    private static void loadInternetExplorerDriverPath(String path) {
        PropertyUtils.setPropertyIfNotExists(IE_DRIVER_PROPERTY_NAME, System.getProperty(INTERNET_EXPLORER_DRIVER_PROPERTY_NAME)); // Alternative property name that follows naming convention
        PropertyUtils.setPropertyIfNotExists(IE_DRIVER_PROPERTY_NAME, path);
        PropertyUtils.setPropertyIfNotExists(IE_DRIVER_PROPERTY_NAME, getInternetExplorerDriverDefaultPath());
    }

    private static void makeSureDriversAreExecutable() {
        FileUtils.makeExecutable(System.getProperty(CHROME_DRIVER_PROPERTY_NAME));
        FileUtils.makeExecutable(System.getProperty(IE_DRIVER_PROPERTY_NAME));
    }

    private static String getChromeDriverDefaultPath() {
        if (OsUtils.isWindows()) {
            return "drivers/chromedriver-windows-32bit.exe";
        } else if (OsUtils.isMac()) {
            return "drivers/chromedriver-mac-32bit";
        } else if (OsUtils.isLinux()) {
            if (OsUtils.is64Bit()) {
                return "drivers/chromedriver-linux-64bit";
            } else {
                return "drivers/chromedriver-linux-32bit";
            }
        }
        return null;
    }

    private static String getInternetExplorerDriverDefaultPath() {
        if (OsUtils.isWindows()) {
            if (PropertyUtils.isTrue(IE_DRIVER_USE64BIT_PROPERTY_NAME)
                    || PropertyUtils.isTrue(INTERNET_EXPLORER_DRIVER_USE64BIT_PROPERTY_NAME)) {
                return "drivers/internetexplorerdriver-windows-64bit.exe";
            } else {
                return "drivers/internetexplorerdriver-windows-32bit.exe";
            }
        }
        return null;
    }
}
