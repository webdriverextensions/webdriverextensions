package org.andidev.webdriverextension.bot.festbot.asserttypes;

import java.util.List;
import org.andidev.webdriverextension.bot.festbot.istypes.IsOptionTypes;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AssertOptionsTypes {

    private final List<WebElement> options;

    public AssertOptionsTypes(WebElement webElement) {
        this.options = webElement.findElements(By.tagName("option"));
    }

    private WebElement findOption(String text) {
        for (WebElement option : options) {
            if (StringUtils.equals(text.trim(), StringUtils.trim(option.getText()))) {
                return option;
            }
        }
        return null;
    }

    private WebElement findOptionWithValue(String value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private WebElement findOptionWithIndex(int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public AssertOptionTypes option(String text) {
        return new AssertOptionTypes(findOption(text));
    }

    public AssertOptionTypes optionWithValue(String value) {
        return new AssertOptionTypes(findOptionWithValue(value));
    }

    public AssertOptionTypes optionWithIndex(int index) {
        return new AssertOptionTypes(findOptionWithIndex(index));
    }


}
