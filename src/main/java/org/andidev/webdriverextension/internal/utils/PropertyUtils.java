package org.andidev.webdriverextension.internal.utils;

public class PropertyUtils {

    public static boolean setPropertyIfNotExists(String name, String value) {
        if (System.getProperty(name) != null) {
            System.setProperty(name, value);
            return true;
        }
        return false;
    }
}
