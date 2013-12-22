package org.webdriverextensions.junitrunner;

import org.webdriverextensions.junitrunner.annotations.Chrome;
import org.webdriverextensions.junitrunner.annotations.Firefox;
import org.webdriverextensions.junitrunner.annotations.HtmlUnit;
import org.webdriverextensions.junitrunner.annotations.InternetExplorer;
import org.webdriverextensions.junitrunner.annotations.Safari;
import static org.webdriverextensions.Bot.*;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
@HtmlUnit
@Safari
public class WebDriverRunnerTest {
    @Test
    public void successfulTest() {
        open("https://github.com");
        assertCurrentUrlStartsWith("https://github.com");
    }
}