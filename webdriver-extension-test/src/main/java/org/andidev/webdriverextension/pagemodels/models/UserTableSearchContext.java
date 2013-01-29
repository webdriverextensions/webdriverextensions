package org.andidev.webdriverextension.pagemodels.models;

import java.util.List;
import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.annotation.ResetSearchContext;
import org.openqa.selenium.support.FindBy;

public class UserTableSearchContext extends WebElement {

    @ResetSearchContext
    @FindBy(css = "#search-query")
    public WebElement searchQuery;
    @ResetSearchContext
    @FindBy(css = "#todo-list li")
    public List<WebElement> todoList;
}
