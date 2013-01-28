package org.andidev.webdriverextension;

import org.openqa.selenium.By;

public interface WebElementFactory {

    <T extends WebElement> T create(Class<T> htmlTagClass, org.openqa.selenium.WebElement webElement, By by);
}