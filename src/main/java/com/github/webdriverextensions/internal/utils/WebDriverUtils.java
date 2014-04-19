package com.github.webdriverextensions.internal.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.openqa.selenium.Capabilities;

public class WebDriverUtils {

    public static String convertToJsonString(Capabilities capabilities) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(capabilities.asMap());
    }

}
