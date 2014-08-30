package com.github.webdriverextensions.model.components;

import java.util.List;
import com.github.webdriverextensions.WebComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserTable extends WebComponent {

    public static class Row extends WebComponent {

        @FindBy(css = ".row")
        public WebElement row;
        @FindBy(css = ".first-name")
        public WebElement firstName;
        @FindBy(css = ".last-name")
        public WebElement lastName;
        @FindBy(css = ".username")
        public WebElement username;

    }
    @FindBy(css = "thead tr")
    public Row header;
    @FindBy(css = "tbody tr")
    public List<Row> rows;
}
