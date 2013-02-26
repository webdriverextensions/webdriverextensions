package org.andidev.webdriverextension.twostepbot.asserters;

import org.junit.Assert;

public class StringIsContainsIsSetAsserter extends StringIsContainsAsserter {

    public StringIsContainsIsSetAsserter(String actualText) {
        super(actualText);
    }

    public void isSet() {
        Assert.assertNull(actualText);
    }

    public void isNotSet() {
        Assert.assertNotNull(actualText);
    }

}
