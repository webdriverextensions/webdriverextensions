package org.andidev.webdriverextension.bot.festbot.asserttypes;

import org.junit.Assert;

public class AssertStringTypes {

    private final String actual;

    public AssertStringTypes(String actual) {
        this.actual = actual;
    }

    public void is(String text) {
        Assert.assertEquals(text, actual);
    }

    public void isNot(String text) {
        Assert.assertNotEquals(text, actual);
    }

    public void contains(String text) {
        Assert.assertTrue(actual.contains(text));
    }

    public void notContains(String text) {
        Assert.assertTrue(!actual.contains(text));
    }

    public void startsWith(String prefix) {
        Assert.assertTrue(actual.startsWith(prefix));
    }

    public void notStartsWith(String prefix) {
        Assert.assertTrue(!actual.startsWith(prefix));
    }

    public void endsWith(String suffix) {
        Assert.assertTrue(actual.endsWith(suffix));
    }

    public void notEndsWith(String suffix) {
        Assert.assertTrue(!actual.endsWith(suffix));
    }
}
