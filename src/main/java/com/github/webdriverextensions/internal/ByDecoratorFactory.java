package com.github.webdriverextensions.internal;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public interface ByDecoratorFactory {
    public WebDriverExtensionsByDecorator create(SearchContext root, WebDriver driver);
}
