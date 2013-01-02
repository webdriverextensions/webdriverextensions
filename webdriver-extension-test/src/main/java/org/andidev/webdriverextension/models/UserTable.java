package org.andidev.webdriverextension.models;

import org.andidev.webdriverextension.HtmlTag;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserTable extends HtmlTag {

    public static class Row extends HtmlTag {
        @FindBy(css = ".row")
        public HtmlTag row;
        @FindBy(css = ".first-name")
        public HtmlTag firstName;
        @FindBy(css = ".last-name")
        public HtmlTag lastName;
        @FindBy(css = ".username")
        public HtmlTag username;

        public Row(WebElement webElement, By by) {
            super(webElement, by);
        }
    }
    @FindBy(css = "thead tr")
    public Row header;
    @FindBy(css = "tbody tr")
    public List<Row> rows;

    public UserTable(WebElement webElement, By by) {
        super(webElement, by);
    }
}
