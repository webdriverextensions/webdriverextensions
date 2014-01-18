package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.Browsers;
import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import com.github.webdriverextensions.junitrunner.annotations.RemoteAddress;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

@RunWith(SeleniumGridRunner.class)
@RemoteAddress("http://andidev:80b7768e-dc06-4d5b-b793-5b3b83f0e24c@ondemand.saucelabs.com:80/wd/hub")
@Browsers(firefox = {
    @Firefox(platform = Platform.WINDOWS)})
public class InheritedRunner {
}
