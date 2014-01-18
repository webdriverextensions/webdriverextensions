package com.github.webdriverextensions.site;

import com.github.webdriverextensions.generator.annotations.Generate;

@Generate(name = "site")
public class WebDriverExtensionSite extends AbstractSite {

    public String url = "http://webdriverextensions.github.com/webdriverextensions/model-test.html";

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        examplesPage.assertIsOpen();
    }
}
