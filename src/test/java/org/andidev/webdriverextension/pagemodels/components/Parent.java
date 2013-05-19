package org.andidev.webdriverextension.pagemodels.components;

import org.andidev.webdriverextension.WebContainer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Parent extends WebContainer {

    @FindBy(css = ".row")
    public WebElement row;
    @FindBy(css = ".first-name")
    public WebElement firstName;
    @FindBy(css = ".last-name")
    public WebElement lastName;
    @FindBy(css = ".username")
    public WebElement username;
}
