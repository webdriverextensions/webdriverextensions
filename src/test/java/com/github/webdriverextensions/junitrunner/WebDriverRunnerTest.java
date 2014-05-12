package com.github.webdriverextensions.junitrunner;

import static com.github.webdriverextensions.Bot.*;
import static com.github.webdriverextensions.WebDriverExtensionsContext.getDriver;
import com.github.webdriverextensions.junitrunner.annotations.Browsers;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.HtmlUnit;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.github.webdriverextensions.junitrunner.annotations.Safari;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

@RunWith(WebDriverRunner.class)
//@Firefox
//@Chrome
//@InternetExplorer
//@HtmlUnit
//@Safari
@Browsers(chrome = {
    @Chrome(desiredCapabilities = "{chromeOptions: {args: ['start-maximized']}}"),
    @Chrome(desiredCapabilitiesClass = StartChromeMaximized.class)
})
public class WebDriverRunnerTest {

    @Test
    public void successfulTest() {
        open("https://github.com");
        assertCurrentUrlStartsWith("https://github.com");
        WebElement element = getDriver().findElement(By.name("q"));
        click(element);
        debug(element);
        waitFor(3);
    }
}
