package org.webdriverextensions.internal;

import org.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;

public interface WebComponentFactory {

    <T extends WebComponent> T create(Class<T> webComponentClass, WebElement webElement);
}