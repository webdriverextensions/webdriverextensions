package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;

public interface WebComponentFactory {

    <T extends WebComponent> T create(Class<T> webComponentClass, WebElement webElement);
}