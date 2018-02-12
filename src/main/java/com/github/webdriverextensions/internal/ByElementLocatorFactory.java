package com.github.webdriverextensions.internal;

import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public interface ByElementLocatorFactory {
    public ElementLocator createLocator(By by);
}
