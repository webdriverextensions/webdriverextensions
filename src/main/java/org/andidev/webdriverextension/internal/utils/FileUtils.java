package org.andidev.webdriverextension.internal.utils;

import java.io.File;

public class FileUtils {

    public static void makeExecutable(String path) {
        File file = new File(path);
        if (file.exists() && !file.canExecute()) {
            file.setExecutable(true);
        }
    }
}
