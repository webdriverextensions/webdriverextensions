package org.andidev.webdriverextension.twostepbot2.asserters;

import org.junit.Assert;

public class AssertStringTypes {

    private final String string;

    public AssertStringTypes(String string) {
        this.string = string;
    }

    public void is(String text) {
        Assert.assertEquals(text, string);
    }

    public void isNot(String text) {
        Assert.assertNotEquals(text, string);
    }

    public void contains(String text) {
        Assert.assertTrue(string.contains(text));
    }

    public void notContains(String text) {
        Assert.assertTrue(!string.contains(text));
    }

    public void startsWith(String prefix) {
        Assert.assertTrue(string.startsWith(prefix));
    }

    public void notStartsWith(String prefix) {
        Assert.assertTrue(!string.startsWith(prefix));
    }

    public void endsWith(String suffix) {
        Assert.assertTrue(string.endsWith(suffix));
    }

    public void notEndsWith(String suffix) {
        Assert.assertTrue(!string.endsWith(suffix));
    }
}
