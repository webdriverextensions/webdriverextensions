package com.github.webdriverextensions.internal.utils;

import java.io.File;
import org.openqa.selenium.Platform;

public class OsUtils {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    public static boolean isWindows() {
        return Platform.WINDOWS.is(Platform.getCurrent());
    }

    public static boolean isMac() {
        return Platform.MAC.is(Platform.getCurrent());
    }

    public static boolean isLinux() {
        return Platform.LINUX.is(Platform.getCurrent());
    }

    public static boolean is64Bit() {
        return com.sun.jna.Platform.is64Bit();
    }

    public static String getUserHomePath() {
        return new File(System.getProperty("user.home")).getAbsolutePath();
    }

    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }
}
