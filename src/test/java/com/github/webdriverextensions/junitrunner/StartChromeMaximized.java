package com.github.webdriverextensions.junitrunner;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class StartChromeMaximized extends DesiredCapabilities {

    public StartChromeMaximized() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        setCapability(ChromeOptions.CAPABILITY, options);
    }
}
