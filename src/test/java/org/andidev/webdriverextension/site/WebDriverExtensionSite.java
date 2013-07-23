package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.generator.annotations.Generate;

@Generate(name = "site")
public class WebDriverExtensionSite extends AbstractSite {

    public String url = "http://andidev.github.com/webdriver-extension";

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        examplesPage.assertIsOpen();
    }
}
