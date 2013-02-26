package org.andidev.webdriverextension.twostepbot.asserters;

import java.util.List;
import org.andidev.webdriverextension.Openable;
import org.andidev.webdriverextension.WebElement;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

public class SelectBoxAsserter {

    private final WebElement webElement;

    public SelectBoxAsserter(WebElement webElement) {
        this.webElement = webElement;
    }

    public void option(String option) {
        List<org.openqa.selenium.WebElement> selectedOptions = new Select(webElement).getAllSelectedOptions();
        Assert.assertNotNull(selectedOptions);
        for (org.openqa.selenium.WebElement selectedOption : selectedOptions) {
            if (StringUtils.equals(StringUtils.trim(selectedOption.getText()), option)) {
                return;
            }
        }
        Assert.fail("Option: " + option + " is not selected!");
    }

}
