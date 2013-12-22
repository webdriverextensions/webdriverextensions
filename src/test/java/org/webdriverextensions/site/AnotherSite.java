package org.webdriverextensions.site;

import org.webdriverextensions.generator.annotations.Generate;

@Generate
public class AnotherSite extends AbstractAnotherSite {

    public String url = "http://webdriverextensions.github.com/webdriverextensions";

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        examplesPage2.assertIsOpen();
    }
}
