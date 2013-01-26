package org.andidev.webdriverextension.page;

import java.util.List;
import org.andidev.webdriverextension.HtmlTag;
import org.andidev.webdriverextension.PageObject;
import static org.andidev.webdriverextension.WebDriverAssert.*;
import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.annotation.Page;
import org.andidev.webdriverextension.models.Menu;
import org.andidev.webdriverextension.models.MenuButtonGroup;
import org.andidev.webdriverextension.models.UserRow;
import org.andidev.webdriverextension.models.UserTableSearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@Page
public class ExamplesPage extends PageObject {

    // Section: WebElements
    @FindBy(css = "#search-query")
    public HtmlTag searchQuery;
    @FindBy(css = "#search")
    public HtmlTag search;
    // Section: Extended WebElements
    @FindBy(css = ".btn-group")
    public MenuButtonGroup menuButtonGroup;
    // Section: List with WebElements
    @FindBy(css = "#todo-list li")
    public List<HtmlTag> todoList;
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
    public ExamplesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void open() {
        getDriver().get("http://andidev.github.com/webdriver-extension/index.html");
    }

    @Override
    public void assertIsOpen() throws Error {
        assertIsDisplayed(searchQuery);
        assertIsDisplayed(search);
        assertIsDisplayed(menu);
    }

    public UserRow findUserRowByFirstName(String firstName) {
        for (UserRow row : rows) {
            if (isText(firstName, row.firstName)) {
                return row;
            }
        }
        return null;
    }
}
