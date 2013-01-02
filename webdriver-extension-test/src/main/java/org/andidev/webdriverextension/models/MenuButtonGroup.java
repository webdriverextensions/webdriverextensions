package org.andidev.webdriverextension.models;

import org.andidev.webdriverextension.HtmlTag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuButtonGroup extends HtmlTag {

    @FindBy(css = "#menu")
    public HtmlTag menu;
    @FindBy(css = "#menu-create")
    public HtmlTag create;
    @FindBy(css = "#menu-update")
    public HtmlTag update;
    @FindBy(css = "#menu-delete")
    public HtmlTag delete;
    
    public MenuButtonGroup(WebElement webElement, By by) {
        super(webElement, by);
    }
}
