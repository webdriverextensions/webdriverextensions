package org.andidev.webdriverextension.pagemodels.components;

import org.andidev.webdriverextension.WebContainer;
import org.andidev.webdriverextension.annotations.Delegate;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends WebContainer {

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
