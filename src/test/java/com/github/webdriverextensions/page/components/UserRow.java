package com.github.webdriverextensions.page.components;

import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRow extends WebComponent {

    @FindBy(css = ".row")
    public WebElement row;
    @FindBy(css = ".first-name")
    public WebElement firstName;
    @FindBy(css = ".last-name")
    public WebElement lastName;
    @FindBy(css = ".username")
    public WebElement username;
}
