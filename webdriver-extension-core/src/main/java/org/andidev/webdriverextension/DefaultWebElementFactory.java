package org.andidev.webdriverextension;

import org.openqa.selenium.By;

public class DefaultWebElementFactory implements WebElementFactory {

    @Override
    public <T extends WebElement> T create(Class<T> htmlTagClass, org.openqa.selenium.WebElement webElement, By by) {
        final T htmlTag = createInstanceOf(htmlTagClass, webElement, by);
        return htmlTag;
    }

    private <T extends WebElement> T createInstanceOf(final Class<T> htmlTagClass, final org.openqa.selenium.WebElement webElement, final By by) {
        try {
            T htmlElement = (T) htmlTagClass.newInstance();
            htmlElement.init(webElement, by);
            return htmlElement;
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