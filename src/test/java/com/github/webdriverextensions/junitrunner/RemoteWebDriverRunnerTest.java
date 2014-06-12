package com.github.webdriverextensions.junitrunner;

import static com.github.webdriverextensions.Bot.assertCurrentUrlContains;
import static com.github.webdriverextensions.Bot.open;
import com.github.webdriverextensions.junitrunner.annotations.Browsers;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.RemoteAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

@RunWith(RemoteWebDriverRunner.class)
@RemoteAddress("http://andidev:80b7768e-dc06-4d5b-b793-5b3b83f0e24c@ondemand.saucelabs.com:80/wd/hub")
@Browsers(
        chrome = {
            @Chrome(platform = Platform.WINDOWS),
            @Chrome(platform = Platform.LINUX),
            @Chrome(platform = Platform.MAC)
        }
)
public class RemoteWebDriverRunnerTest {

    @Test
    public void openGoogleTest() {
        open("https://github.com");
        assertCurrentUrlContains("github");
    }
}
