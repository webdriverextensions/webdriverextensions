package org.andidev.webdriverextension;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface WebContainerFactory {

    <T extends WebContainer> T create(Class<T> webContainerClass, WebElement webElement, By by);
}