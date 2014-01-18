package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.Safari;
import static com.github.webdriverextensions.Bot.*;
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