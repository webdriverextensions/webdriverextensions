package org.andidev.webdriverextension.models;

import java.util.List;
import static org.andidev.webdriverextension.WebDriverBot.*;
import org.andidev.webdriverextension.WebElement;
import org.openqa.selenium.support.FindBy;

public class Table extends WebElement {

    @FindBy(css = "th")
    public List<WebElement> header;
    @FindBy(css = "td")
    public List<WebElement> rows;

    public Integer getHeader(String header) {
        int i = 0;
        for (WebElement htmlTag : this.header) {
            if (isText(header, htmlTag)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }

    public Integer getHeaderContains(String header) {
        int i = 0;
        for (WebElement htmlTag : this.header) {
            if (isTextContaining(header, htmlTag)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }

    public Integer getHeaderStrartsWith(String header) {
        int i = 0;
        for (WebElement htmlTag : this.header) {
            if (isTextStartingWith(header, htmlTag)) {
                return new Integer(i);
            }
            i++;
        }
        return null;
    }

    public Integer getHeaderEndsWith(String header) {
        int i = 0;
        for (WebElement htmlTag : this.header) {
            if (isTextEndingWith(header, htmlTag)) {
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