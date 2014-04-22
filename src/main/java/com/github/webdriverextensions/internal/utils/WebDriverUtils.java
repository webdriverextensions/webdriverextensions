package com.github.webdriverextensions.internal.utils;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Map;
import java.util.Set;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverUtils {

    public static String convertToJsonString(Capabilities capabilities) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(capabilities.asMap());
    }

    public static Capabilities removeCapabilities(Capabilities capabilities, String... keysToRemove) {
        if (capabilities == null) {
            return new DesiredCapabilities();
        }

        final Set<String> keysToRemoveSet = Sets.newHashSet(keysToRemove);
        capabilities = new DesiredCapabilities(Maps.filterKeys(capabilities.asMap(), new Predicate<String>() {
            @Override
            public boolean apply(String key) {
                return !keysToRemoveSet.contains(key);
            }
        }));

        return capabilities;
    }

    public static Capabilities addCapabilities(Capabilities capabilities, Map<String, Object> capabilitiesToAdd) {
        if (capabilities == null && capabilitiesToAdd == null) {
            return new DesiredCapabilities();
        }

        if (capabilities == null) {
            return new DesiredCapabilities(capabilitiesToAdd);
        }

        if (capabilitiesToAdd == null) {
            return capabilities;
        }

        return new DesiredCapabilities(capabilities, new DesiredCapabilities(capabilitiesToAdd));
    }
}
