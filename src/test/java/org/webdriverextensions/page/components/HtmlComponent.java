package org.webdriverextensions.page.components;

import org.webdriverextensions.WebComponent;
import org.openqa.selenium.support.FindBy;

public class HtmlComponent extends WebComponent {

    @FindBy(css = ".btn-group")
    public Menu menu;
}
