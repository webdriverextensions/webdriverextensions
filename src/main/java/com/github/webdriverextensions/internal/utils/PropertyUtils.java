package com.github.webdriverextensions.internal.utils;

import org.apache.commons.lang3.BooleanUtils;

public class PropertyUtils {

    public static boolean isTrue(String key) {
        return BooleanUtils.toBoolean(System.getProperty(key));
    }

    public static boolean propertyExists(String key) {
        if (System.getProperty(key) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void setPropertyIfNotExists(String key, String value) {
        if (value == null || StringUtils.isBlank(value)) {
            return;
        }
        if (!propertyExists(key)) {
            System.setProperty(key, value);
        }
    }
}
