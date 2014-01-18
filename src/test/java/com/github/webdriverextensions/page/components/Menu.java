package com.github.webdriverextensions.page.components;

import com.github.webdriverextensions.WebComponent;
import com.github.webdriverextensions.annotations.Delegate;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends WebComponent {

    @Delegate
    @FindBy(css = "#menu")
    public WebElement menu;
    @FindBy(css = "#menu-create")
    public WebElement create;
    @FindBy(css = "#menu-update")
    public WebElement update;
    @FindBy(css = "#menu-delete")
    public WebElement delete;
}
