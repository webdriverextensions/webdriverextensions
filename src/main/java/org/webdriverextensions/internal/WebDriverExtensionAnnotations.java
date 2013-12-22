package org.webdriverextensions.internal;

import java.lang.reflect.Field;
import org.webdriverextensions.WebComponent;
import org.webdriverextensions.annotations.Delegate;
import org.webdriverextensions.annotations.ResetSearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

public class WebDriverExtensionAnnotations extends Annotations {

    private Field field;

    public WebDriverExtensionAnnotations(Field field) {
        super(field);
        this.field = field;
    }

    boolean isSearchContextReset() {
        return (field.getAnnotation(ResetSearchContext.class) != null);
    }

    public static WebElement getDelagate(WebComponent webComponent) {
        Field[] fields = ReflectionUtils.getAnnotatedDeclaredFields(webComponent.getClass(), Delegate.class);
        if (fields.length == 0) {
            return null;
        }
        if (fields.length > 1) {
            throw new RuntimeException("More than one @Delagate annotation used. There should only exist one.");
        }
        WebElement delegate;
        try {
            delegate = (WebElement) fields[0].get(webComponent);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return delegate;
    }
}
