package org.andidev.webdriverextension.internal.junitrunner;

import org.andidev.webdriverextension.internal.WebDriverExtensionException;
import org.andidev.webdriverextension.internal.utils.FileUtils;
import org.andidev.webdriverextension.internal.utils.OsUtils;
import org.andidev.webdriverextension.junitrunner.annotations.DriverPaths;
import org.apache.commons.lang3.StringUtils;

public class DriverPathLoader {

    private static String CHROME_DRIVER_PROPERTY_KEY = "webdriver.chrome.driver";
    private static String INTERNET_EXPLORER_DRIVER_PROPERTY_KEY = "webdriver.ie.driver";
    private static String ALTERNATIVE_INTERNET_EXPLORER_DRIVER_PROPERTY_KEY = "webdriver.internetexplorer.driver";

    public static void loadDriverPaths(DriverPaths driverPaths) {
        setChromeDriverDriverPath(driverPaths != null ? driverPaths.chrome() : "");
        setInternetExplorerDriverPath(driverPaths != null ? driverPaths.internetExplorer() : "");
        makeSureDriversAreExecutable();
    }

    private static void setChromeDriverDriverPath(String path) {
        if (System.getProperty(CHROME_DRIVER_PROPERTY_KEY) != null) {
            return;
        }
        if (StringUtils.isNotBlank(path)) {
            System.setProperty(CHROME_DRIVER_PROPERTY_KEY, path);
            return;
        }
        System.setProperty(CHROME_DRIVER_PROPERTY_KEY, getChromeDriverDefaultPath());
    }

    private static void setInternetExplorerDriverPath(String path) {
        if (System.getProperty(INTERNET_EXPLORER_DRIVER_PROPERTY_KEY) != null) {
            return;
        }
        if (System.getProperty(ALTERNATIVE_INTERNET_EXPLORER_DRIVER_PROPERTY_KEY) != null) {
            System.setProperty(INTERNET_EXPLORER_DRIVER_PROPERTY_KEY, System.getProperty(ALTERNATIVE_INTERNET_EXPLORER_DRIVER_PROPERTY_KEY));
            return;
        }
        if (StringUtils.isNotBlank(path)) {
            System.setProperty(INTERNET_EXPLORER_DRIVER_PROPERTY_KEY, path);
            return;
        }
        System.setProperty(INTERNET_EXPLORER_DRIVER_PROPERTY_KEY, getInternetExplorerDriverDefaultPath());
    }

    private static void makeSureDriversAreExecutable() {
        FileUtils.makeExecutable(System.getProperty(CHROME_DRIVER_PROPERTY_KEY));
        FileUtils.makeExecutable(System.getProperty(INTERNET_EXPLORER_DRIVER_PROPERTY_KEY));
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
        throw new WebDriverExtensionException("You are using an unsuported platform. Platform = " + OsUtils.getOsName() + ", Version = " + OsUtils.getOsVersion());
    }

    private static String getInternetExplorerDriverDefaultPath() {
        return "drivers/windows/internetexplorerdriver.exe";
    }
}
