package org.webdriverextensions.internal.junitrunner;

import org.webdriverextensions.internal.utils.FileUtils;
import org.webdriverextensions.internal.utils.OsUtils;
import org.webdriverextensions.internal.utils.PropertyUtils;
import org.webdriverextensions.junitrunner.annotations.DriverPaths;

public class DriverPathLoader {

    private static String CHROME_DRIVER_PROPERTY_NAME = "webdriver.chrome.driver";
    private static String IE_DRIVER_PROPERTY_NAME = "webdriver.ie.driver";
    private static String INTERNET_EXPLORER_DRIVER_PROPERTY_NAME = "webdriver.internetexplorer.driver"; // Alternative property name that follows naming convention
    private static String IE_DRIVER_USE64BIT_PROPERTY_NAME = "webdriver.ie.driver.use64Bit";
    private static String INTERNET_EXPLORER_DRIVER_USE64BIT_PROPERTY_NAME = "webdriver.internetexplorer.driver.use64Bit";

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
            return "drivers/windows/chromedriver.exe";
        } else if (OsUtils.isMac()) {
            return "drivers/mac/chromedriver";
        } else if (OsUtils.isLinux()) {
            if (OsUtils.is64Bit()) {
                return "drivers/linux/chromedriver64bit";
            } else {
                return "drivers/linux/chromedriver";
            }
        }
        return null;
    }

    private static String getInternetExplorerDriverDefaultPath() {
        if (OsUtils.isWindows()) {
            if (PropertyUtils.isTrue(IE_DRIVER_USE64BIT_PROPERTY_NAME)
                    || PropertyUtils.isTrue(INTERNET_EXPLORER_DRIVER_USE64BIT_PROPERTY_NAME)) {
                return "drivers/windows/internetexplorerdriver64bit.exe";
            } else {
                return "drivers/windows/internetexplorerdriver.exe";
            }
        }
        return null;
    }
}
