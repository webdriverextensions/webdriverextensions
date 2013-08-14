package org.andidev.webdriverextension.internal.utils;

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
        return Platform.getCurrent().equals(Platform.WINDOWS);
    }

    public static boolean isWindows8() {
        return Platform.getCurrent().equals(Platform.WIN8);
    }

    public static boolean isMac() {
        return Platform.getCurrent().equals(Platform.MAC);
    }

    public static boolean isLinux() {
        return Platform.getCurrent().equals(Platform.LINUX);
    }

    public static boolean isAndroid() {
        return Platform.getCurrent().equals(Platform.ANDROID);
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
