package com.github.webdriverextensions.internal;

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class WebDriverExtensionsByDecoratorFactory implements ByDecoratorFactory {
    @Override
    public WebDriverExtensionsByDecorator create(SearchContext root, WebDriver driver) {
        return new WebDriverExtensionsByDecorator(
                new WebDriverExtensionsByElementLocatorFactory(root), driver);
    }
}