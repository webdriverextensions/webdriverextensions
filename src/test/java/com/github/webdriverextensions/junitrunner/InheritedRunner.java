package com.github.webdriverextensions.junitrunner;

import com.github.webdriverextensions.junitrunner.annotations.Firefox;
import org.junit.runner.RunWith;
import org.openqa.selenium.Platform;

@RunWith(WebDriverRunner.class)
@Firefox(platform = Platform.WINDOWS)
public abstract class InheritedRunner {
}
