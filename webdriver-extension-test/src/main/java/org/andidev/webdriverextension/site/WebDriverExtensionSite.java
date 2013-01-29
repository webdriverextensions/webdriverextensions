package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.annotation.SiteObject;
import org.openqa.selenium.WebDriver;

@SiteObject
public class WebDriverExtensionSite extends WebSite {

    public WebDriverExtensionSite(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUrl() {
        return "http://andidev.github.com/webdriver-extension";
    }

    @Override
    public void assertIsOpen() throws Error {
        examplesPage.assertIsOpen();
    }
}
