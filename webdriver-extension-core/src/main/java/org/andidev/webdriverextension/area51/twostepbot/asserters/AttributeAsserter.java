package org.andidev.webdriverextension.area51.twostepbot.asserters;

import org.andidev.webdriverextension.WebElement;

public class AttributeAsserter {

    private String attributeName;

    public AttributeAsserter(String attributeName) {
        this.attributeName = attributeName;
    }

    public StringIsContainsIsSetAsserter in(WebElement webElement) {
        return new StringIsContainsIsSetAsserter(webElement.getAttribute(attributeName));
    }
}
