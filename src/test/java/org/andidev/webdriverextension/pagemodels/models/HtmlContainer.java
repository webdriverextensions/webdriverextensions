package org.andidev.webdriverextension.pagemodels.models;

import org.andidev.webdriverextension.WebContainer;
import org.openqa.selenium.support.FindBy;

public class HtmlContainer extends WebContainer {

    @FindBy(css = ".btn-group")
    public Menu menu;
}
