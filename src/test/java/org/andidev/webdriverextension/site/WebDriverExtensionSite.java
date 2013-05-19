package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.generator.annotations.Generate;
import org.andidev.webdriverextension.annotations.SiteAwareExtends;

@Generate(name = "site")
@SiteAwareExtends({DriverAware.class})
public class WebDriverExtensionSite extends AbstractSite {

    @Override
    public String getUrl() {
        return "http://andidev.github.com/webdriver-extension";
    }

    @Override
    public void assertIsOpen() throws Error {
        examplesPage.assertIsOpen();
    }

}
