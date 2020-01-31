package com.github.webdriverextensions.internal;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class WebDriverExtensionsByElementLocatorFactory implements ByElementLocatorFactory {

    private final SearchContext root;
    
    public WebDriverExtensionsByElementLocatorFactory(SearchContext root) {
        this.root = root;
    }

    @Override
    public ElementLocator createLocator(By by) {
        return new ByElementLocator(root, by);
    }

}
