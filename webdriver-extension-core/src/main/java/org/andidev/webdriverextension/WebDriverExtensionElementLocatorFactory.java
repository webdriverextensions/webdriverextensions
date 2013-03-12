package org.andidev.webdriverextension;

import java.lang.reflect.Field;
import org.andidev.webdriverextension.annotation.ResetSearchContext;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.support.pagefactory.DefaultElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class WebDriverExtensionElementLocatorFactory implements ElementLocatorFactory {

    private final SearchContext searchContext;
    private final SearchContext resetSearchContext;

    /**
     * Creates a new element locator.
     *
     * @param searchContext The context to use when finding the element
     * @param field The field on the Page Object that will hold the located
     * value
     */
    public WebDriverExtensionElementLocatorFactory(SearchContext searchContext, SearchContext resetSearchContext) {
        this.searchContext = searchContext;
        this.resetSearchContext = resetSearchContext;
    }

    @Override
    public ElementLocator createLocator(Field field) {
        if (hasAnnotatedResetSearchContext(field)) {
            return new DefaultElementLocator(resetSearchContext, field);
        } else {
            return new DefaultElementLocator(searchContext, field);
        }
    }

    private boolean hasAnnotatedResetSearchContext(Field field) {
        ResetSearchContext annotation = (ResetSearchContext) field.getAnnotation(ResetSearchContext.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }
}
