package com.github.webdriverextensions.site;

import com.github.webdriverextensions.generator.annotations.Generate;

@Generate
public class AnotherSite extends AbstractAnotherSite {

    public String url = "http://webdriverextensions.github.com/webdriverextensions/model-test.html";

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        examplesPage2.assertIsOpen();
    }
}
