package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.annotation.Site;
import org.openqa.selenium.WebDriver;

@Site
public class GoogleSite extends SiteObject {

    public GoogleSite(WebDriver driver) {
        super(driver);
    }

    @Override
    public String getUrl() {
        return "https://www.google.com/";
    }

    @Override
    public void assertIsOpen() throws Error {
        searchPage.isOpen();
    }
}
