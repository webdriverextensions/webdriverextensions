package org.andidev.webdriverextension.internal.utils;

import java.io.File;

public class OsUtils {

    private static String OS = System.getProperty("os.name").toLowerCase();

    public static String getOsName() {
        return System.getProperty("os.name");
    }

    public static String getOsVersion() {
        return System.getProperty("os.version");
    }

    public static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    public static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    public static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }

    public static boolean isLinux() {
        return (OS.indexOf("linux") >= 0);
    }

    public static boolean isSolaris() {
        return (OS.indexOf("sunos") >= 0);
    }

    public static String getUserHomePath() {
        return new File(System.getProperty("user.home")).getAbsolutePath();
    }

    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }
}
