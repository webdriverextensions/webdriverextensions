package org.webdriverextensions.internal;

import org.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;

public class DefaultWebComponentFactory implements WebComponentFactory {

    @Override
    public <T extends WebComponent> T create(Class<T> webComponentClass, WebElement webElement) {
        final T webComponent = createInstanceOf(webComponentClass, webElement);
        return webComponent;
    }

    private <T extends WebComponent> T createInstanceOf(final Class<T> webComponentClass, final WebElement webElement) {
        try {
            T webComponent = (T) webComponentClass.newInstance();
            webComponent.init(webElement);
            return webComponent;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}