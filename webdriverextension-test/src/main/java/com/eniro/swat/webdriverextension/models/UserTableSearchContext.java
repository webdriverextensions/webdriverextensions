package com.eniro.swat.webdriverextension.models;

import com.eniro.swat.webdriverextension.HtmlTag;
import com.eniro.swat.webdriverextension.annotation.ResetSearchContext;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserTableSearchContext extends HtmlTag {
    @ResetSearchContext
    @FindBy(css = "#search-query")
    public HtmlTag searchQuery;
    @ResetSearchContext
    @FindBy(css = "#todo-list li")
    public List<HtmlTag> todoList;

    public UserTableSearchContext(WebElement webElement, By by) {
        super(webElement, by);
    }
}
