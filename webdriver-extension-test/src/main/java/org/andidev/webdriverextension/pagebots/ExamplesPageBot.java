package org.andidev.webdriverextension.pagebots;

import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.annotation.PageObject;
import org.andidev.webdriverextension.pagemodels.ExamplesPageModel;
import org.andidev.webdriverextension.pagemodels.models.UserRow;

@PageObject(name = "examplesPage")
public class ExamplesPageBot extends ExamplesPageModel {

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
