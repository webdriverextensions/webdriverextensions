package org.andidev.webdriverextension.internal;

import java.util.List;
import org.andidev.webdriverextension.WebContainer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WebContainerListFactory {

    <T extends WebContainer> List<T> create(Class<T> webContainerClass, List<WebElement> webElements, WebDriver driver);
}