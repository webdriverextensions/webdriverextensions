package com.github.webdriverextensions.internal.utils;

import org.openqa.selenium.Platform;

public class OsUtils {

    public static boolean isWindows() {
        return Platform.getCurrent().is(Platform.WINDOWS);
    }

    public static boolean isMac() {
        return Platform.getCurrent().is(Platform.MAC);
    }

    public static boolean isLinux() {
        return Platform.getCurrent().is(Platform.LINUX);
    }

    public static boolean isCurrentPlatform(String platform) {
        try {
            return Platform.getCurrent().is(Platform.valueOf(platform));
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean is64Bit() {
        return com.sun.jna.Platform.is64Bit();
    }
}
