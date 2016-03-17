package com.github.webdriverextensions.internal.junitrunner;

import com.github.webdriverextensions.internal.utils.PropertyUtils;
import static com.github.webdriverextensions.internal.utils.WebDriverUtils.SCREENSHOTSPATH_PROPERTY_NAME;
import com.github.webdriverextensions.junitrunner.annotations.ScreenshotsPath;

public class ScreenshotsPathLoader {

    private ScreenshotsPathLoader() {}
    
    public static void loadScreenshotsPath(ScreenshotsPath screenshotsPathAnnotation) {
        String screenshotsPathAnnotationValue = screenshotsPathAnnotation != null ? screenshotsPathAnnotation.value() : null;
        PropertyUtils.setPropertyIfNotExists(SCREENSHOTSPATH_PROPERTY_NAME, screenshotsPathAnnotationValue);
    }

}
