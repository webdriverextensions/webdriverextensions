package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.annotation.SiteObject;

@SiteObject
public class WebDriverExtensionSite extends SiteModel {

    @Override
    public String getUrl() {
        return "http://andidev.github.com/webdriver-extension";
    }

    @Override
    public void assertIsOpen() throws Error {
        examplesPage.assertIsOpen();
    }

}
