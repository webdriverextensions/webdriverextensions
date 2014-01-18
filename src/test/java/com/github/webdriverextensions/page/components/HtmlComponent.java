package com.github.webdriverextensions.page.components;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.support.FindBy;

public class HtmlComponent extends WebComponent {

    @FindBy(css = ".btn-group")
    public Menu menu;
}
