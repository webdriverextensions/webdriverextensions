package org.andidev.webdriverextension.internal;

import org.andidev.webdriverextension.WebComponent;
import org.openqa.selenium.WebElement;

public interface WebComponentFactory {

    <T extends WebComponent> T create(Class<T> webComponentClass, WebElement webElement);
}