package org.webdriverextensions.page;

import java.util.List;
import static org.webdriverextensions.Bot.*;
import static org.webdriverextensions.ThreadDriver.*;
import org.webdriverextensions.WebPage;
import org.webdriverextensions.generator.annotations.Generate;
import org.webdriverextensions.page.components.HtmlComponent;
import org.webdriverextensions.page.components.Menu;
import org.webdriverextensions.page.components.MenuButtonGroup;
import org.webdriverextensions.page.components.UserRow;
import org.webdriverextensions.page.components.UserTableSearchContext;
import org.webdriverextensions.site.WebDriverExtensionSite;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Generate(name = "examplesPage")
public class ExamplesPage extends WebPage<WebDriverExtensionSite> {

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
    public List<WebElement> todos;
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
    @FindBy(css = "html")
    public HtmlComponent html;

    @Override
    public void open() {
        getDriver().get("http://webdriverextensions.github.com/webdriverextensions/index.html");
    }

    @Override
    public void assertIsOpen() throws AssertionError {
//        assertIsDisplayed(searchQuery);
//        assertIsDisplayed(search);
//        assertIsDisplayed(menu);
    }

    public UserRow findUserRowByFirstName(String firstName) {
        for (UserRow row : rows) {
            if (textEquals(firstName, row.firstName)) {
                return row;
            }
        }
        return null;
    }
}
