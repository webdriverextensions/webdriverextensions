package com.eniro.swat.webdriverextension.models;

import com.eniro.swat.webdriverextension.HtmlTag;
import com.eniro.swat.webdriverextension.annotation.Delegate;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Menu extends HtmlTag {

    @Delegate
    @FindBy(css = "#menu")
    public HtmlTag menu;
    @FindBy(css = "#menu-create")
    public HtmlTag create;
    @FindBy(css = "#menu-update")
    public HtmlTag update;
    @FindBy(css = "#menu-delete")
    public HtmlTag delete;
    
    public Menu(WebElement webElement, By by) {
        super(webElement, by);
    }
}
