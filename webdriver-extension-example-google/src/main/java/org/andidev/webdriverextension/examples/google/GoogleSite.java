package org.andidev.webdriverextension.examples.google;

import org.andidev.webdriverextension.annotation.SiteObject;
import org.openqa.selenium.WebDriver;

@SiteObject(name = "googleSite")
public class GoogleSite extends SiteModel {

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
