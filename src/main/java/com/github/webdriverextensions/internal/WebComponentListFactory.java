package com.github.webdriverextensions.internal;

import java.util.List;
import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WebComponentListFactory {

    <T extends WebComponent> List<T> create(Class<T> webComponentClass, List<WebElement> webElements, WebDriver driver);
}