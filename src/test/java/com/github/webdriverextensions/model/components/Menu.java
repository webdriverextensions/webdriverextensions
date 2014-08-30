package com.github.webdriverextensions.model.components;

import com.github.webdriverextensions.WebComponent;
import com.github.webdriverextensions.annotations.Delegate;
import com.github.webdriverextensions.generator.annotations.AddToRepository;
import com.github.webdriverextensions.generator.annotations.AddToWebSite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

@FindBy(how = How.CSS, using = ".btn-group")
//@FindBys({@FindBy(how = How.CSS, using = ".btn-group")})
@AddToWebSite
@AddToRepository
public class Menu extends WebComponent {

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
