package org.andidev.webdriverextension.models;

import org.andidev.webdriverextension.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserRow extends WebElement {

    @FindBy(css = ".row")
    public WebElement row;
    @FindBy(css = ".first-name")
    public WebElement firstName;
    @FindBy(css = ".last-name")
    public WebElement lastName;
    @FindBy(css = ".username")
    public WebElement username;
}
