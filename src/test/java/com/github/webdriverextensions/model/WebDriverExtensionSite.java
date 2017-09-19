package com.github.webdriverextensions.model;

import com.github.webdriverextensions.WebPage;
import com.github.webdriverextensions.generator.annotations.AddToRepository;
import com.github.webdriverextensions.model.pages.ExamplesPage;

@AddToRepository
public class WebDriverExtensionSite extends WebPage {

	public ExamplesPage examplesPage;
	
    public final String url = "file://" + getClass().getResource("/html/model-test.html").getPath();

    @Override
    public void open(Object... arguments) {
        open(url);
    }

    @Override
    public void assertIsOpen(Object... arguments) throws AssertionError {
        assertIsOpen();
    }
}
