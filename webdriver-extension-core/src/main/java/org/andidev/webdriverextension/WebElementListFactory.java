package org.andidev.webdriverextension;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public interface WebElementListFactory {

    <T extends WebElement> List<T> create(Class<T> htmlTagClass, List<org.openqa.selenium.WebElement> webElements, By by, WebDriver driver);
}