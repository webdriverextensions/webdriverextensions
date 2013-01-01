package com.eniro.swat.webdriverextension;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DefaultHtmlTagFactory implements HtmlTagFactory {

    @Override
    public <T extends HtmlTag> T create(Class<T> htmlTagClass, WebElement webElement, By by) {
        final T htmlTag = createInstanceOf(htmlTagClass, webElement, by);
        return htmlTag;
    }

    private <T extends HtmlTag> T createInstanceOf(final Class<T> htmlTagClass, final WebElement webElement, final By by) {
        try {
            Constructor<T> constructor = htmlTagClass.getConstructor(WebElement.class, By.class);
            return constructor.newInstance(webElement, by);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
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
