package org.andidev.webdriverextension.twostepbot.asserters;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;

public class StringIsContainsAsserter extends StringIsAsserter {

    public StringIsContainsAsserter(String actualText) {
        super(actualText);
    }

    public void contains(String text) {
        Assert.assertTrue(StringUtils.contains(actualText, text));
    }

    public void notContains(String text) {
        Assert.assertTrue(!StringUtils.contains(actualText, text));
    }

    public void startsWith(String prefix) {
        Assert.assertTrue(StringUtils.startsWith(actualText, prefix));
    }

    public void notStartsWith(String prefix) {
        Assert.assertTrue(!StringUtils.startsWith(actualText, prefix));
    }

    public void endsWith(String suffix) {
        Assert.assertTrue(StringUtils.endsWith(actualText, suffix));
    }

    public void notEndsWith(String suffix) {
        Assert.assertTrue(!StringUtils.endsWith(actualText, suffix));
    }
}
