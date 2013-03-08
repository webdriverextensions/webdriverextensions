package org.andidev.webdriverextension;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DefaultWebContainerListFactory implements WebContainerListFactory {

    private WebContainerFactory htmlTagFactory;

    public DefaultWebContainerListFactory(WebContainerFactory htmlTagFactory) {
        this.htmlTagFactory = htmlTagFactory;
    }

    @Override
    public <T extends WebContainer> List<T> create(Class<T> htmlTagClass, List<WebElement> webElements, By by, WebDriver driver) {
        return new WebContainerList<T>(htmlTagClass, webElements, htmlTagFactory, by, driver);
    }
}