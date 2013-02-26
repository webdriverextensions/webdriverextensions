package org.andidev.webdriverextension.pagemodels;

import java.util.List;

import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.pagemodels.models.Menu;
import org.andidev.webdriverextension.pagemodels.models.MenuButtonGroup;
import org.andidev.webdriverextension.pagemodels.models.UserRow;
import org.andidev.webdriverextension.pagemodels.models.UserTableSearchContext;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
import org.openqa.selenium.support.FindBy;

public abstract class ExamplesPageModel extends WebPage<WebDriverExtensionSite> {

    // Section: WebElements
    @FindBy(css = "#search-query")
    public WebElement searchQuery;
    @FindBy(css = "#search")
    public WebElement search;
    // Section: Extended WebElements
    @FindBy(css = ".btn-group")
    public MenuButtonGroup menuButtonGroup;
    @FindBy(css = ".btn-group")
    public MenuButtonGroup menuButtonGroups;
    // Section: List with WebElements
    @FindBy(css = "#todo-list li")
    public List<WebElement> todoList;
    // Section: List with Extended WebElements
    @FindBy(css = "#user-table tbody tr")
    public List<UserRow> rows;
    // Search Context Testing
    @FindBy(css = "#user-table")
    public UserTableSearchContext userTableSearchContext;
    // Wrapper Testing
    @FindBy(css = ".btn-group")
    public Menu menu;
//    @FindBy(css = "#user-table")
//    public UserTable userTable;

    public ExamplesPageModel() {
    }


}
