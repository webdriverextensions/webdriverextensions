package org.andidev.webdriverextension;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DefaultWebContainerListFactory implements WebContainerListFactory {

    private WebContainerFactory webContainerFactory;

    public DefaultWebContainerListFactory(WebContainerFactory webContainerFactory) {
        this.webContainerFactory = webContainerFactory;
    }

    @Override
    public <T extends WebContainer> List<T> create(Class<T> webContainerClass, List<WebElement> webElements, By by, WebDriver driver) {
        return new WebContainerList<T>(webContainerClass, webElements, webContainerFactory, by, driver);
    }
}