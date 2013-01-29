package org.andidev.webdriverextension.pagemodels.models;

import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.annotation.Delegate;
import org.openqa.selenium.support.FindBy;

public class Menu extends WebElement {

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
