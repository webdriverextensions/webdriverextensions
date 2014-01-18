package com.github.webdriverextensions.internal;

import java.lang.reflect.Field;
import java.util.List;
import com.github.webdriverextensions.annotations.ResetSearchContext;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class WebDriverExtensionElementLocator implements ElementLocator {

    private final SearchContext searchContext;
    private final boolean shouldCache;
    private final By by;
    private WebElement cachedElement;
    private List<WebElement> cachedElementList;

    public WebDriverExtensionElementLocator(SearchContext searchContext, Field field, WebDriver driver) {
        WebDriverExtensionAnnotations annotations = new WebDriverExtensionAnnotations(field);
        if (annotations.isSearchContextReset()) {
            this.searchContext = driver;
        } else {
            this.searchContext = searchContext;
        }
        shouldCache = annotations.isLookupCached();
        by = annotations.buildBy();
    }

    @Override
    public WebElement findElement() {
        if (cachedElement != null && shouldCache) {
            return cachedElement;
        }

        WebElement element = searchContext.findElement(by);
        if (shouldCache) {
            cachedElement = element;
        }

        return element;
    }

    @Override
    public List<WebElement> findElements() {
        if (cachedElementList != null && shouldCache) {
            return cachedElementList;
        }

        List<WebElement> elements = searchContext.findElements(by);
        if (shouldCache) {
            cachedElementList = elements;
        }

        return elements;
    }

    private boolean hasAnnotatedResetSearchContext(Field field) {
        ResetSearchContext annotation = (ResetSearchContext) field.getAnnotation(ResetSearchContext.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }
}
