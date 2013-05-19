package org.andidev.webdriverextension.pagebots;

import static org.andidev.webdriverextension.JUnitBot.*;
import org.andidev.webdriverextension.generator.annotations.Generate;
import org.andidev.webdriverextension.pagemodels.HomePageModel;

@Generate(name = "homePage")
public class HomePageBot extends HomePageModel {

    @Override
    public void open() {
        getDriver().get("http://andidev.github.com/webdriver-extension/index.html");
    }

    @Override
    public void assertIsOpen() throws Error {
        assertIsDisplayed(examples);
    }
}
