package com.eniro.swat.webdriverextension.models;

import com.eniro.swat.webdriverextension.HtmlTag;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRow extends HtmlTag {
    @FindBy(css = ".row")
    public HtmlTag row;
    @FindBy(css = ".first-name")
    public HtmlTag firstName;
    @FindBy(css = ".last-name")
    public HtmlTag lastName;
    @FindBy(css = ".username")
    public HtmlTag username;

    public UserRow(WebElement webElement, By by) {
        super(webElement, by);
    }
}
