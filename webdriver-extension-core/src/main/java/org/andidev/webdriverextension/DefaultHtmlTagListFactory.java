package org.andidev.webdriverextension;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DefaultHtmlTagListFactory implements HtmlTagListFactory {
    
    private HtmlTagFactory htmlTagFactory;

    public DefaultHtmlTagListFactory(HtmlTagFactory htmlTagFactory) {
        this.htmlTagFactory = htmlTagFactory;
    }

    @Override
    public <T extends HtmlTag> List<T> create(Class<T> htmlTagClass, List<WebElement> webElements, By by, WebDriver driver) {
        return new HtmlTagList<T>(htmlTagClass, webElements, htmlTagFactory, by, driver);
    }
}
