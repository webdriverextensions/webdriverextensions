package org.andidev.webdriverextension.bot.fest.istypes;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class IsOptionsTypes {

    private final List<WebElement> options;

    public IsOptionsTypes(WebElement webElement) {
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

    public IsOptionTypes option(String text) {
        return new IsOptionTypes(findOption(text));
    }

    public IsOptionTypes optionWithValue(String value) {
        return new IsOptionTypes(findOptionWithValue(value));
    }

    public IsOptionTypes optionWithIndex(int index) {
        return new IsOptionTypes(findOptionWithIndex(index));
    }
}
