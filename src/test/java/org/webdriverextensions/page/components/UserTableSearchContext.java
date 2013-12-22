package org.webdriverextensions.page.components;

import java.util.List;
import org.webdriverextensions.WebComponent;
import org.webdriverextensions.annotations.ResetSearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserTableSearchContext extends WebComponent {

    @ResetSearchContext
    @FindBy(css = "#search-query")
    public WebElement searchQuery;
    @ResetSearchContext
    @FindBy(css = "#todo-list li")
    public List<WebElement> todos;
}
