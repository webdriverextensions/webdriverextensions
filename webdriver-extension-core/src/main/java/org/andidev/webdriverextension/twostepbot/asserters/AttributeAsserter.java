package org.andidev.webdriverextension.twostepbot.asserters;

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
