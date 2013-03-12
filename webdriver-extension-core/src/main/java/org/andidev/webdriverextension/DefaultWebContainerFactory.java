package org.andidev.webdriverextension;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DefaultWebContainerFactory implements WebContainerFactory {

    @Override
    public <T extends WebContainer> T create(Class<T> webContainerClass, WebElement webElement, By by) {
        final T webContainer = createInstanceOf(webContainerClass, webElement, by);
        return webContainer;
    }

    private <T extends WebContainer> T createInstanceOf(final Class<T> webContainerClass, final WebElement webElement, final By by) {
        try {
            T webContainer = (T) webContainerClass.newInstance();
            webContainer.init(webElement, by);
            return webContainer;
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