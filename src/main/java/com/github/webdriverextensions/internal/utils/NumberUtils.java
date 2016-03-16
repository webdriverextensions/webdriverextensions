package com.github.webdriverextensions.internal.utils;

public class NumberUtils {

    private NumberUtils() {}
    
    public static String toString(double number) {
        if (number == (int) number) {
            return String.format("%d", (int) number);
        } else {
            return String.format("%s", number);
        }
    }
}
