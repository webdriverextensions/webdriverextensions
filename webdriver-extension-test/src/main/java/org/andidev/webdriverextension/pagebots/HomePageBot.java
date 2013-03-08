package org.andidev.webdriverextension.pagebots;

import static org.andidev.webdriverextension.bot.JunitBot.*;
import org.andidev.webdriverextension.annotation.PageObject;
import org.andidev.webdriverextension.pagemodels.HomePageModel;
import org.openqa.selenium.WebDriver;

@PageObject(name = "homePage")
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
