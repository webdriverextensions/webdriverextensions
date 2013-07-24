package org.andidev.webdriverextension.internal.utils;

public class NumberUtils {

    public static String toString(double number) {
        if (number == (int) number) {
            return String.format("%d", (int) number);
        } else {
            return String.format("%s", number);
        }
    }
}
