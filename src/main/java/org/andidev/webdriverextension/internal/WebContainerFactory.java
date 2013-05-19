package org.andidev.webdriverextension.internal;

import org.andidev.webdriverextension.WebContainer;
import org.openqa.selenium.WebElement;

public interface WebContainerFactory {

    <T extends WebContainer> T create(Class<T> webContainerClass, WebElement webElement);
}