package org.andidev.webdriverextension.area51.twostepbot.asserters;

import org.andidev.webdriverextension.Openable;
import org.junit.Assert;

public class BrowserUrlAsserter extends StringIsContainsAsserter {

    public BrowserUrlAsserter(String actualText) {
        super(actualText);
    }

    public void matches(Openable openable) {
        Assert.assertTrue(actualText.matches(openable.getUrl()));
    }
}
