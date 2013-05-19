package org.andidev.webdriverextension.internal.area51.festbot;

import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class AssertWebElementTypes {

    private final WebElement webElement;

    public AssertWebElementTypes(WebElement webElement) {
        this.webElement = webElement;
    }

    public void is(String text) {
        Assert.assertEquals(text, webElement.getText());
    }

    public void isNot(String text) {
        Assert.assertNotEquals(text, webElement.getText());
    }

    public void contains(String text) {
        Assert.assertTrue(webElement.getText().contains(text));
    }

    public void notContains(String text) {
        Assert.assertTrue(!webElement.getText().contains(text));
    }

    public void startsWith(String prefix) {
        Assert.assertTrue(webElement.getText().startsWith(prefix));
    }

    public void notStartsWith(String prefix) {
        Assert.assertTrue(!webElement.getText().startsWith(prefix));
    }

    public void endsWith(String suffix) {
        Assert.assertTrue(webElement.getText().endsWith(suffix));
    }

    public void notEndsWith(String suffix) {
        Assert.assertTrue(!webElement.getText().endsWith(suffix));
    }
}
