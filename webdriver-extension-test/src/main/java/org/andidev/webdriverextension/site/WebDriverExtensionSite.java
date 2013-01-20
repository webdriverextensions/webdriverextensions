package org.andidev.webdriverextension.site;

import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.annotation.Site;
import static org.junit.Assert.fail;
import org.openqa.selenium.WebDriver;

@Site
public class WebDriverExtensionSite extends WebDriverExtensionSiteObject<WebDriverExtensionSite> {

    private WebDriver driver;
    
    public WebDriverExtensionSite(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    @Override
    public String getUrl() {
        return "http://andidevelopment.github.com/webdriver-extension";
    }

    @Override
    public void isLoaded() throws Error {
        try {
            examplesPage.isLoaded();
        } catch (AssertionError e) {
            fail(this.getClass().getSimpleName() + " is not loaded");
        }
    }
    
}
