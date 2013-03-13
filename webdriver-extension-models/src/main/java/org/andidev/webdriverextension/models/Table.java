package org.andidev.webdriverextension.models;

import java.util.List;
import org.andidev.webdriverextension.WebContainer;
import static org.andidev.webdriverextension.bot.JUnitBot.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Table extends WebContainer {

    @FindBy(css = "th")
    public List<WebElement> header;
    @FindBy(css = "td")
    public List<WebElement> rows;

    public Integer getHeader(String header) {
        int i = 0;
        for (WebElement webElement : this.header) {
            if (isText(header, webElement)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }

    public Integer getHeaderContains(String header) {
        int i = 0;
        for (WebElement webElement : this.header) {
            if (isTextContaining(header, webElement)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }

    public Integer getHeaderStrartsWith(String header) {
        int i = 0;
        for (WebElement webElement : this.header) {
            if (isTextStartingWith(header, webElement)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }

    public Integer getHeaderEndsWith(String header) {
        int i = 0;
        for (WebElement webElement : this.header) {
            if (isTextEndingWith(header, webElement)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }
//    public List<WebElement> getRowWhere(String header, String value) {
//        Integer header = ;getHeader(header);
//        List<WebElement> header = ;getHeader(header);
//    }
}