package com.github.webdriverextensions.internal.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.ScreenshotsPath;
import java.lang.annotation.Annotation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import org.junit.Before;
import org.junit.Test;

public class ScreenshotsPathLoaderTest {

    @Before
    public void clearScreenshotPathSystemProperty() {
        System.clearProperty("webdriverextensions.screenshotspath");
    }

    @Test
    public void testLoadScreenshotsPath() {
        final String anyScreenshotsPath = "anyscreenshotspath";
        ScreenshotsPath annotation = new ScreenshotsPath() {

            @Override
            public String value() {
                return anyScreenshotsPath;
            }

            @Override
            public Class<? extends Annotation> annotationType() {
                throw new UnsupportedOperationException("Not tested");
            }
        };

        ScreenshotsPathLoader.loadScreenshotsPath(annotation);
        assertThat(System.getProperty("webdriverextensions.screenshotspath"), is(equalTo(anyScreenshotsPath)));
    }

}
