package org.andidev.webdriverextension;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DefaultWebElementListFactory implements WebElementListFactory {

    private WebElementFactory htmlTagFactory;

    public DefaultWebElementListFactory(WebElementFactory htmlTagFactory) {
        this.htmlTagFactory = htmlTagFactory;
    }

    @Override
    public <T extends WebElement> List<T> create(Class<T> htmlTagClass, List<org.openqa.selenium.WebElement> webElements, By by, WebDriver driver) {
        return new WebElementList<T>(htmlTagClass, webElements, htmlTagFactory, by, driver);
    }
}