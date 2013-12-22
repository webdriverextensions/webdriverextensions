package org.webdriverextensions.site;

import org.webdriverextensions.generator.annotations.Generate;

@Generate(name = "site")
public class WebDriverExtensionSite extends AbstractSite {

    public String url = "http://webdriverextensions.github.com/webdriverextensions";

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        examplesPage.assertIsOpen();
    }
}
