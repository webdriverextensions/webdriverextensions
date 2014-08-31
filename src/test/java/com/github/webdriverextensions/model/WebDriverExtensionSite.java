package com.github.webdriverextensions.model;

import com.github.webdriverextensions.generator.GeneratedWebSite;
import com.github.webdriverextensions.generator.annotations.AddToRepository;

@AddToRepository
public class WebDriverExtensionSite extends GeneratedWebSite {

    public final String url = "file://" + getClass().getResource("/html/model-test.html").getPath();

    @Override
    public void open(Object... arguments) {
        open(url);
    }

    @Override
    public void assertIsOpen(Object... arguments) throws AssertionError {
        examplesPage.assertIsOpen();
    }
}
