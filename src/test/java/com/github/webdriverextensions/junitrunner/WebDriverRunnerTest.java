package com.github.webdriverextensions.junitrunner;

import static com.github.webdriverextensions.Bot.*;
import com.github.webdriverextensions.junitrunner.annotations.Browsers;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.Safari;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(WebDriverRunner.class)
@Firefox
@Chrome
@InternetExplorer
//@HtmlUnit // TODO: Enable when selenium java issue is solved
//@Safari Disabled until bug is solved, see https://code.google.com/p/selenium/issues/detail?id=7933
@Browsers(chrome = {
    @Chrome(desiredCapabilities = "{chromeOptions: {args: ['start-maximized']}}"),
    @Chrome(desiredCapabilitiesClass = StartChromeMaximized.class)
})
public class WebDriverRunnerTest {

    @Test
    public void successfulTest() {
        open("https://github.com");
        assertCurrentUrlContains("github");
    }
}
