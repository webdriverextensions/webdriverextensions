package com.github.webdriverextensions.internal.junitrunner;

import com.github.webdriverextensions.internal.utils.FileUtils;
import com.github.webdriverextensions.internal.utils.OsUtils;
import com.github.webdriverextensions.internal.utils.PropertyUtils;
import com.github.webdriverextensions.junitrunner.annotations.DriverPaths;

import static com.github.webdriverextensions.WebDriverExtensionsProperties.IE_DRIVER_USE64BIT_PROPERTY_NAME;
import static com.github.webdriverextensions.WebDriverExtensionsProperties.INTERNET_EXPLORER_DRIVER_PROPERTY_NAME;
import static com.github.webdriverextensions.WebDriverExtensionsProperties.INTERNET_EXPLORER_DRIVER_USE64BIT_PROPERTY_NAME;
import static com.github.webdriverextensions.WebDriverProperties.*;

public class DriverPathLoader {

    private DriverPathLoader() {}

    public static void loadDriverPaths(DriverPaths driverPaths) {
        loadChromeDriverPath(driverPaths != null ? driverPaths.chrome() : null);
        loadEdgeDriverPath(driverPaths != null ? driverPaths.edge() : null);
        loadInternetExplorerDriverPath(driverPaths != null ? driverPaths.internetExplorer() : null);
        loadPhantomJsDriverPath(driverPaths != null ? driverPaths.internetExplorer() : null);
        makeSureDriversAreExecutable();
    }

    private static void loadChromeDriverPath(String path) {
        PropertyUtils.setPropertyIfNotExists(CHROME_DRIVER_PROPERTY_NAME, path);
        PropertyUtils.setPropertyIfNotExists(CHROME_DRIVER_PROPERTY_NAME, getChromeDriverDefaultPath());
    }

    private static void loadEdgeDriverPath(String path) {
        PropertyUtils.setPropertyIfNotExists(EDGE_DRIVER_PROPERTY_NAME, path);
        PropertyUtils.setPropertyIfNotExists(EDGE_DRIVER_PROPERTY_NAME, getEdgeDefaultPath());
    }

    private static void loadInternetExplorerDriverPath(String path) {
        PropertyUtils.setPropertyIfNotExists(IE_DRIVER_PROPERTY_NAME, System.getProperty(INTERNET_EXPLORER_DRIVER_PROPERTY_NAME)); // Alternative property name that follows naming convention
        PropertyUtils.setPropertyIfNotExists(IE_DRIVER_PROPERTY_NAME, path);
        PropertyUtils.setPropertyIfNotExists(IE_DRIVER_PROPERTY_NAME, getInternetExplorerDriverDefaultPath());
    }

    private static void loadPhantomJsDriverPath(String path) {
        PropertyUtils.setPropertyIfNotExists(PHANTOMJS_BINARY_PROPERTY_NAME, path);
        PropertyUtils.setPropertyIfNotExists(PHANTOMJS_BINARY_PROPERTY_NAME, getPhantomJsDefaultPath());
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

    private static String getEdgeDefaultPath() {
        if (OsUtils.isWindows()) {
            if (OsUtils.is64Bit()) {
                return "drivers/edgedriver-windows-64bit";
            } else {
                return "drivers/edgedriver-windows-32bit";
            }
        }
        return null;
    }

    private static String getPhantomJsDefaultPath() {
        if (OsUtils.isWindows()) {
            return "drivers/phantomjs-windows-64bit.exe";
        } else if (OsUtils.isMac()) {
            return "drivers/phantomjs-mac-64bit";
        } else if (OsUtils.isLinux()) {
            if (OsUtils.is64Bit()) {
                return "drivers/phantomjs-linux-64bit";
            } else {
                return "drivers/phantomjs-linux-32bit";
            }
        }
        return null;
    }

    private static String getInternetExplorerDriverDefaultPath() {
        if (OsUtils.isWindows()) {
            if (!PropertyUtils.propertyExists(IE_DRIVER_USE64BIT_PROPERTY_NAME)
                    || !PropertyUtils.propertyExists(INTERNET_EXPLORER_DRIVER_USE64BIT_PROPERTY_NAME)) {
                if (OsUtils.isWindows10()) {
                    return "drivers/internetexplorerdriver-windows-64bit.exe";
                } else {
                    return "drivers/internetexplorerdriver-windows-32bit.exe";
                }
            } else {
                if (PropertyUtils.isTrue(IE_DRIVER_USE64BIT_PROPERTY_NAME)
                        || PropertyUtils.isTrue(INTERNET_EXPLORER_DRIVER_USE64BIT_PROPERTY_NAME)) {
                    return "drivers/internetexplorerdriver-windows-64bit.exe";
                } else {
                    return "drivers/internetexplorerdriver-windows-32bit.exe";
                }
            }
        }
        return null;
    }
}
