package org.andidev.webdriverextension.internal;

import java.util.List;
import org.andidev.webdriverextension.WebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WebComponentListFactory {

    <T extends WebComponent> List<T> create(Class<T> webComponentClass, List<WebElement> webElements, WebDriver driver);
}