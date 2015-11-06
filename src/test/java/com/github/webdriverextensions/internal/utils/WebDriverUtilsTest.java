package com.github.webdriverextensions.internal.utils;

import static java.io.File.separator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.endsWith;
import org.junit.Before;
import org.junit.Test;

public class WebDriverUtilsTest {

    @Before
    public void clearScreenshotPathSystemProperty() {
        System.clearProperty("webdriverextensions.screenshotspath");
    }

    @Test
    public void testThatScreenshotsPathIsSetToDefaultValue() {
        String anyFileName = "anyfilename";
        String screenshotDirectory = WebDriverUtils.getScreenshotFilePath(anyFileName);
        assertThat(screenshotDirectory, endsWith("webdriverextensions" + separator + "screenshots" + separator +
                anyFileName + ".png"));
    }

    @Test
    public void testThatScreenshotsPathCanBeSetToRelativePathValue() {
        String anyFileName = "anyfilename";
        System.setProperty("webdriverextensions.screenshotspath", "screenshots");
        String screenshotDirectory = WebDriverUtils.getScreenshotFilePath(anyFileName);
        assertThat(screenshotDirectory, endsWith("webdriverextensions" + separator + "screenshots" + separator +
                anyFileName + ".png"));
    }

    @Test
    public void testThatScreenshotsPathCanBeSetToAbsolutePathValue() {
        String anyFileName = "anyfilename";
        System.setProperty("webdriverextensions.screenshotspath", separator + "screenshots");
        String screenshotDirectory = WebDriverUtils.getScreenshotFilePath(anyFileName);
        assertThat(screenshotDirectory, endsWith(separator + "screenshots" + separator + anyFileName + ".png"));
    }

}
