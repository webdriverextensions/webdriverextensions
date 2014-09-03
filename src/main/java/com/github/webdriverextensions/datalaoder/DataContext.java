package com.github.webdriverextensions.datalaoder;

import com.github.webdriverextensions.internal.WebDriverExtensionException;
import static com.github.webdriverextensions.internal.utils.StringUtils.quote;
import java.io.IOException;
import java.util.Properties;

public class DataContext {

    private static ThreadLocal<Properties> threadLocalData = new ThreadLocal<Properties>();

    public static void init() {
        threadLocalData.set(new Properties());
    }

    public static void destroy() {
        threadLocalData.remove();
    }

    public static void loadDataFromPropertyFile(String filename) {
        try {
            threadLocalData.get().load(Thread.currentThread().getContextClassLoader().getResourceAsStream(filename));
        } catch (IOException e) {
            // Be forgiving and swallow misspelled file paths
        }
    }

    public static String getData(String key) {
        String localization = System.getProperty("localization");
        String environment = System.getProperty("environment");
        String value;

        if (localization != null && environment != null) {
            value = threadLocalData.get().getProperty(key + addSeparator(localization) + addSeparator(environment));
            if (value != null) {
                return value;
            }
        }
        if (localization != null) {
            value = threadLocalData.get().getProperty(key + addSeparator(localization));
            if (value != null) {
                return value;
            }
        }
        if (environment != null) {
            value = threadLocalData.get().getProperty(key + addSeparator(environment));
            if (value != null) {
                return value;
            }
        }
        value = threadLocalData.get().getProperty(key);
        if (value != null) {
            return value;
        }
        throw new WebDriverExtensionException("Could not load data for key "
                + quote(key) + " (localization = " + localization + ", environment = " + environment + ")");
    }

    public static Properties getAllData() {
        return threadLocalData.get();
    }

    private static String addSeparator(String string) {
        return "[" + string + "]";
    }

}
