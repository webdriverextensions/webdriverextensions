package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.annotation.Generate;

@Generate(name = "LoginSite")
public class LoginSite extends LoginSiteModel {

    @Override
    public String getUrl() {
        return "http://andidev.github.com/webdriver-extension";
    }

    @Override
    public void assertIsOpen() throws Error {
        examplesPage2.assertIsOpen();
    }

}
