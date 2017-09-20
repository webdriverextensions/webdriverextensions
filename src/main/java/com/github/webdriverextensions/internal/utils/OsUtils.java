package com.github.webdriverextensions.internal.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.openqa.selenium.Platform;

public class OsUtils {

    private OsUtils() {
    }

    public static boolean isWindows() {
        return Platform.getCurrent().is(Platform.WINDOWS);
    }

    public static boolean isWindows10() {
        return Platform.getCurrent().is(Platform.WIN10);
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

    public static String getCurrentUserName() {
        return System.getenv("USERDOMAIN") + "\\" + System.getenv("USERNAME");
    }

    public static String getComputerHostName() {
        String hostName = "Unknown";
        try {
            InetAddress netAddress;
            netAddress = InetAddress.getLocalHost();
            hostName = netAddress.getCanonicalHostName();
        } catch (UnknownHostException ex) {
        }
        return hostName;
    }
}
