package com.eniro.swat.webdriverextension.page;

import com.eniro.swat.webdriverextension.DefaultHtmlTagFieldDecorator;
import com.eniro.swat.webdriverextension.HtmlTag;
import static com.eniro.swat.webdriverextension.WebDriverBot.*;
import com.eniro.swat.webdriverextension.WebPage;
import com.eniro.swat.webdriverextension.annotation.Page;
import com.eniro.swat.webdriverextension.models.MenuButtonGroup;
import com.eniro.swat.webdriverextension.models.Menu;
import com.eniro.swat.webdriverextension.models.UserRow;
import com.eniro.swat.webdriverextension.models.UserTableSearchContext;
import java.util.List;
import static org.junit.Assert.fail;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
        getDriver().get("http://mrjamesbrown.github.com/webdriver-extension/index.html");
    }

    @Override
    public void isLoaded() throws Error {
        try {
            getDriver().findElement(By.cssSelector("body.webdriverextension-site"));
        } catch (NoSuchElementException e) {
            fail("Cannot locate ExamplesPage");
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
