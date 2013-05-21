package org.andidev.webdriverextension.page;

import java.util.List;
import static org.andidev.webdriverextension.Bot.*;
import static org.andidev.webdriverextension.ThreadDriver.*;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.generator.annotations.Generate;
import org.andidev.webdriverextension.page.components.HtmlContainer;
import org.andidev.webdriverextension.page.components.Menu;
import org.andidev.webdriverextension.page.components.MenuButtonGroup;
import org.andidev.webdriverextension.page.components.UserRow;
import org.andidev.webdriverextension.page.components.UserTableSearchContext;
import org.andidev.webdriverextension.site.WebDriverExtensionSite;
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
    public HtmlContainer html;

    @Override
    public void open() {
        getDriver().get("http://andidev.github.com/webdriver-extension/index.html");
    }

    @Override
    public void assertIsOpen() throws Error {
//        assertIsDisplayed(searchQuery);
//        assertIsDisplayed(search);
//        assertIsDisplayed(menu);
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
