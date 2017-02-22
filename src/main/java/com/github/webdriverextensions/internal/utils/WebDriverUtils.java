package com.github.webdriverextensions.internal.utils;

import com.github.webdriverextensions.junitrunner.annotations.ImplicitlyWait;
import com.github.webdriverextensions.junitrunner.annotations.ScreenshotsPath;
import com.github.webdriverextensions.junitrunner.annotations.TakeScreenshotOnFailure;
import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FilenameUtils;
import static org.apache.commons.io.FilenameUtils.concat;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.TestClass;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

public class WebDriverUtils {

    private WebDriverUtils() {}

    public static String convertToJsonString(Capabilities capabilities) {
        if (capabilities == null) {
            return "{}";
        }
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

    public static Capabilities addCapabilities(Capabilities capabilities, Map<String, ?> capabilitiesToAdd) {
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

    public static boolean hasScreenshotPathAnnotation(TestClass testClass) {
        return testClass.getJavaClass().getAnnotation(ScreenshotsPath.class) != null;
    }

    public static String getValueFromScreenshotPathAnnotation(TestClass testClass) {
        return testClass.getJavaClass().getAnnotation(ScreenshotsPath.class).value();
    }

    public static boolean hasTakeScreenshotOnFailureAnnotation(TestClass testClass) {
        return testClass.getJavaClass().getAnnotation(TakeScreenshotOnFailure.class) != null;
    }

    public static final String SCREENSHOTSPATH_PROPERTY_NAME = "webdriverextensions.screenshotspath";
    public static final String SCREENSHOTSPATH_PROPERTY_DEFAULT_VALUE = "screenshots";

    public static String getScreenshotFilePath(String filename) {
        return FilenameUtils.concat(getScreenshotsPath(), appendFileExtensionsIfNeeded(filename));
    }

    public static String getCurrentDateAndTime() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH.mm");
        return df.format(new Date());
    }

    private static String getScreenshotsPath() {
        String workingDirectory = System.getProperty("user.dir");
        String screenshotPath = System.getProperty(SCREENSHOTSPATH_PROPERTY_NAME);
        System.out.println("screenshotPath = " + screenshotPath);
        if (screenshotPath == null) {
            screenshotPath = SCREENSHOTSPATH_PROPERTY_DEFAULT_VALUE;
        }
        System.out.println("workingDirectory = " + workingDirectory);
        System.out.println("screenshotPath = " + screenshotPath);
        return concat(workingDirectory, screenshotPath);
    }

    private static String appendFileExtensionsIfNeeded(String filename) {
        String extension = FilenameUtils.getExtension(filename);
        if (extension.equals("jpg")
                || extension.equals("jpeg")
                || extension.equals("png")) {
            return filename;
        } else {
            return filename + ".png";
        }
    }

    public static boolean hasImplicitlyWaitAnnotation(TestClass testClass, FrameworkMethod testMethod) {
        return hasImplicitlyWaitTestClassAnnotation(testClass) || hasImplicitlyWaitTestMethodAnnotation(testMethod);
    }

    public static long getValueFromImplicitlyWaitAnnotation(TestClass testClass, FrameworkMethod testMethod) {
        if (hasImplicitlyWaitTestMethodAnnotation(testMethod)) {
            return testMethod.getAnnotation(ImplicitlyWait.class).value();
        } else {
            return testClass.getJavaClass().getAnnotation(ImplicitlyWait.class).value();
        }
    }

    public static TimeUnit getUnitFromImplicitlyWaitAnnotation(TestClass testClass, FrameworkMethod testMethod) {
        if (hasImplicitlyWaitTestMethodAnnotation(testMethod)) {
            return testMethod.getAnnotation(ImplicitlyWait.class).unit();
        } else {
            return testClass.getJavaClass().getAnnotation(ImplicitlyWait.class).unit();
        }
    }

    private static boolean hasImplicitlyWaitTestClassAnnotation(TestClass testClass) {
        return testClass.getJavaClass().getAnnotation(ImplicitlyWait.class) != null;
    }

    private static boolean hasImplicitlyWaitTestMethodAnnotation(FrameworkMethod testMethod) {
        return testMethod.getAnnotation(ImplicitlyWait.class) != null;
    }
}
