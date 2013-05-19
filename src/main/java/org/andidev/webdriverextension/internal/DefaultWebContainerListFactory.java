package org.andidev.webdriverextension.internal;

import java.util.List;
import org.andidev.webdriverextension.WebContainer;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DefaultWebContainerListFactory implements WebContainerListFactory {

    private WebContainerFactory webContainerFactory;

    public DefaultWebContainerListFactory(WebContainerFactory webContainerFactory) {
        this.webContainerFactory = webContainerFactory;
    }

    @Override
    public <T extends WebContainer> List<T> create(Class<T> webContainerClass, List<WebElement> webElements, WebDriver driver) {
        return new WebContainerList<T>(webContainerClass, webElements, webContainerFactory, driver);
    }
}