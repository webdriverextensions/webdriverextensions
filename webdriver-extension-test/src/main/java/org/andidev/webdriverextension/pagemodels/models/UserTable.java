package org.andidev.webdriverextension.pagemodels.models;

import java.util.List;
import org.andidev.webdriverextension.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserTable extends WebElement {

    public static class Row extends WebElement {

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
