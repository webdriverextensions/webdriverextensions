/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.webdriverextensions.internal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Locatable;
import org.openqa.selenium.internal.WrapsElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler;

public class WebDriverExtensionsByDecorator implements ByDecorator {

    private final ByElementLocatorFactory factory;

    public WebDriverExtensionsByDecorator(ByElementLocatorFactory factory,
            WebDriver webDriver) {
        this.factory = factory;
    }

    protected WebElement proxyForLocator(ClassLoader loader, ElementLocator locator, By by) {
        InvocationHandler handler = new LocatingElementHandler(locator);
        return (WebElement) Proxy.newProxyInstance(loader, new Class[]{WebElement.class, WrapsElement.class, Locatable.class}, handler);
    }

    protected List<WebElement> proxyForListLocator(ClassLoader loader, ElementLocator locator, By by) {
        InvocationHandler handler = new CustomLocatingElementListHandler(locator);     
        return (List<WebElement>) Proxy.newProxyInstance(loader, new Class[]{List.class}, handler);
    }

    @Override
    public WebElement decorate(By by) {
        return proxyForLocator(WebElement.class.getClassLoader(), factory.createLocator(by), by);
    }

    @Override
    public List<WebElement> decorateList(By by)  {
         return proxyForListLocator(ArrayList.class.getClassLoader(), factory.createLocator(by), by);
    }
}
