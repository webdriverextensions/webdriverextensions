package org.andidev.webdriverextension.examples.google;

import org.andidev.webdriverextension.annotation.SiteObject;

@SiteObject(name = "googleSite")
public class GoogleSite extends SiteModel {

    @Override
    public String getUrl() {
        return "https://www.google.com/";
    }

    @Override
    public void assertIsOpen() throws Error {
        searchPage.isOpen();
    }
}
