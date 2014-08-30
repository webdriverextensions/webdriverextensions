package com.github.webdriverextensions.model.components;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.support.FindBy;

public class HtmlComponent extends WebComponent {

    @FindBy(css = ".btn-group")
    public Menu menu;
}
