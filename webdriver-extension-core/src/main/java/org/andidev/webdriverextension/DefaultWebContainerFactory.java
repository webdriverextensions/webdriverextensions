package org.andidev.webdriverextension;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DefaultWebContainerFactory implements WebContainerFactory {

    @Override
    public <T extends WebContainer> T create(Class<T> htmlTagClass, WebElement webElement, By by) {
        final T htmlTag = createInstanceOf(htmlTagClass, webElement, by);
        return htmlTag;
    }

    private <T extends WebContainer> T createInstanceOf(final Class<T> htmlTagClass, final WebElement webElement, final By by) {
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