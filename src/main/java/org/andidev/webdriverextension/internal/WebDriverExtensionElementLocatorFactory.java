package org.andidev.webdriverextension.internal;

import java.lang.reflect.Field;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class WebDriverExtensionElementLocatorFactory implements ElementLocatorFactory {

    private final WebDriver driver;
    private final SearchContext searchContext;

    /**
     * Creates a new element locator.
     *
     * @param searchContext The context to use when finding the element
     * @param field The field on the Page Object that will hold the located
     * value
     */
    public WebDriverExtensionElementLocatorFactory(SearchContext searchContext, WebDriver driver) {
        this.searchContext = searchContext;
        this.driver = driver;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        return new WebDriverExtensionElementLocator(searchContext, field, driver);
    }
}
