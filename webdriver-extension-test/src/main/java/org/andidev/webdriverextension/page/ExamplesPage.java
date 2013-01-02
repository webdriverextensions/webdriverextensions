package org.andidev.webdriverextension.page;

import java.util.List;
import org.andidev.webdriverextension.DefaultHtmlTagFieldDecorator;
import org.andidev.webdriverextension.HtmlTag;
import static org.andidev.webdriverextension.WebDriverAssert.*;
import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.annotation.Page;
import org.andidev.webdriverextension.models.Menu;
import org.andidev.webdriverextension.models.MenuButtonGroup;
import org.andidev.webdriverextension.models.UserRow;
import org.andidev.webdriverextension.models.UserTableSearchContext;
import static org.junit.Assert.fail;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Page
public class ExamplesPage extends WebPage<ExamplesPage> {

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
        // Init page elements
        PageFactory.initElements(new DefaultHtmlTagFieldDecorator(driver), this);
    }

    @Override
    public void load() {
        getDriver().get("http://andidevelopment.github.com/webdriver-extension/index.html");
    }

    @Override
    public void isLoaded() throws Error {
        try {
            assertIsDisplayed(searchQuery);
            assertIsDisplayed(search);
            assertIsDisplayed(menu);
        } catch (AssertionError e) {
            fail(this.getClass().getSimpleName() + " is not loaded");
        }

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
