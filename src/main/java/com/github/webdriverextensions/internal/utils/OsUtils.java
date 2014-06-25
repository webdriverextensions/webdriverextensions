package com.github.webdriverextensions.internal.utils;

import java.io.File;
import org.openqa.selenium.Platform;

public class OsUtils {

    public static boolean isWindows() {
        return Platform.WINDOWS.is(Platform.getCurrent());
    }

    public static boolean isMac() {
        return Platform.MAC.is(Platform.getCurrent());
    }

    public static boolean isLinux() {
        return Platform.LINUX.is(Platform.getCurrent());
    }

    public static boolean isCurrentPlatform(String platform) {
        try {
            return Platform.valueOf(platform).is(Platform.getCurrent());
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean is64Bit() {
        return com.sun.jna.Platform.is64Bit();
    }
}
