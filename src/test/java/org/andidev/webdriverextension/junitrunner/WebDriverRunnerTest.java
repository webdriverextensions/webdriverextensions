package org.andidev.webdriverextension.junitrunner;

import org.andidev.webdriverextension.junitrunner.annotations.Chrome;
import org.andidev.webdriverextension.junitrunner.annotations.Firefox;
import org.andidev.webdriverextension.junitrunner.annotations.HtmlUnit;
import org.andidev.webdriverextension.junitrunner.annotations.InternetExplorer;
import org.andidev.webdriverextension.junitrunner.annotations.Safari;
import static org.andidev.webdriverextension.Bot.*;
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