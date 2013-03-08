package org.andidev.webdriverextension.pagemodels.models;

import java.util.List;
import org.andidev.webdriverextension.WebContainer;
import org.andidev.webdriverextension.annotation.ResetSearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserTableSearchContext extends WebContainer {

    @ResetSearchContext
    @FindBy(css = "#search-query")
    public WebElement searchQuery;
    @ResetSearchContext
    @FindBy(css = "#todo-list li")
    public List<WebElement> todoList;
}
