package org.andidev.webdriverextension.page.components;

import org.andidev.webdriverextension.WebContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRow extends WebContainer {

    @FindBy(css = ".row")
    public WebElement row;
    @FindBy(css = ".first-name")
    public WebElement firstName;
    @FindBy(css = ".last-name")
    public WebElement lastName;
    @FindBy(css = ".username")
    public WebElement username;
}
