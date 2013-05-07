package org.andidev.webdriverextension;

import java.lang.reflect.Field;
import org.andidev.webdriverextension.annotation.Delegate;
import org.andidev.webdriverextension.annotation.ResetSearchContext;
import org.andidev.webdriverextension.utils.ReflectionUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.Annotations;

class WebDriverExtensionAnnotations extends Annotations {

    private Field field;

    public WebDriverExtensionAnnotations(Field field) {
        super(field);
        this.field = field;
    }

    boolean isSearchContextReset() {
        return (field.getAnnotation(ResetSearchContext.class) != null);
    }

    public static WebElement getDelagate(WebContainer webContainer) {
        Field[] fields = ReflectionUtils.getAnnotatedDeclaredFields(webContainer.getClass(), Delegate.class);
        if (fields.length == 0) {
            return null;
        }
        if (fields.length > 1) {
            throw new RuntimeException("More than one @Delagate annotation used. There should only exist one.");
        }
        WebElement delegate;
        try {
            delegate = (WebElement) fields[0].get(webContainer);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return delegate;
    }
}
