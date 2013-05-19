package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.generator.annotations.Generate;

@Generate(name = "LoginSite")
public class LoginSite extends AbstractLoginSite {

    @Override
    public String getUrl() {
        return "http://andidev.github.com/webdriver-extension";
    }

    @Override
    public void assertIsOpen() throws Error {
        examplesPage2.assertIsOpen();
    }

}
