package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.annotation.SiteAwareExtends;
import org.andidev.webdriverextension.annotation.SiteObject;
import temp.DriverAware;

@SiteObject(name = "site")
@SiteAwareExtends({DriverAware.class})
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
