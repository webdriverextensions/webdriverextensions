package com.github.webdriverextensions.site;

import com.github.webdriverextensions.generator.annotations.Generate;
import com.github.webdriverextensions.generator.GeneratedWebSite;

@Generate
public class WebDriverExtensionSite extends GeneratedWebSite {

    public final String url = "file://" + getClass().getResource("/html/model-test.html").getPath();

    @Override
    public void open() {
        open(url);
    }

    @Override
    public void assertIsOpen() throws AssertionError {
        examplesPage.assertIsOpen();
    }
}
