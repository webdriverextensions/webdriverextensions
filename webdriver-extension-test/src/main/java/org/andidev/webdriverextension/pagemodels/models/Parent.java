package org.andidev.webdriverextension.pagemodels.models;

import org.andidev.webdriverextension.WebElement;
import org.openqa.selenium.support.FindBy;

public class Parent extends WebElement {

    @FindBy(css = ".row")
    public WebElement row;
    @FindBy(css = ".first-name")
    public WebElement firstName;
    @FindBy(css = ".last-name")
    public WebElement lastName;
    @FindBy(css = ".username")
    public WebElement username;
}
