package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.BooleanOption;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.IgnoreFirefox;
import com.github.webdriverextensions.junitrunner.annotations.RemoteAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

@RunWith(SeleniumGridRunner.class)
@RemoteAddress("http://andidev:80b7768e-dc06-4d5b-b793-5b3b83f0e24c@ondemand.saucelabs.com:80/wd/hub")
@Firefox(platform = Platform.MAC, javascriptEnabled = BooleanOption.TRUE, version = "15", desiredCapabilities = "{}")
public class IgnoreBrowserTest {

    @Test
    @IgnoreFirefox
    public void ignoredTest() {
        assert false;
    }
}