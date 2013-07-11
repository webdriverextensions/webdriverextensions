package org.andidev.webdriverextension.site;

import org.andidev.webdriverextension.generator.annotations.Generate;

@Generate
public class AnotherSite extends AbstractAnotherSite {

    @Override
    public String getUrl() {
        return "http://andidev.github.com/webdriver-extension";
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        examplesPage2.assertIsOpen();
    }

}
