package org.andidev.webdriverextension;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface HtmlTagFactory {
    <T extends HtmlTag> T create(Class<T> htmlTagClass, WebElement webElement, By by);
}
