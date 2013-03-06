package org.andidev.webdriverextension.area51.twostepbot.asserters;

import org.junit.Assert;

public class StringIsAsserter {

    protected final String actualText;

    public StringIsAsserter(String actualText) {
        this.actualText = actualText;
    }

    public void is(String text) {
        Assert.assertEquals(actualText, text);
    }

    public void isNot(String text) {
        Assert.assertNotEquals(actualText, text);
    }
}
