package org.andidev.webdriverextension.twostepbot.asserters;

import org.junit.Assert;

public class NumberAsserter {

    private final Double actualNumber;

    public NumberAsserter(String actualNumber) {
        this.actualNumber = new Double(actualNumber);
    }

    public void is(Double number) {
        Assert.assertEquals(actualNumber, number);
    }

    public void isNot(Double number) {
        Assert.assertNotEquals(actualNumber, number);
    }

    public void isSmallerThen(Double number) {
        Assert.assertTrue(actualNumber < number);
    }

    public void isNotSmallerThen(Double number) {
        Assert.assertTrue(!(actualNumber < number));
    }

    public void isLargerThen(Double number) {
        Assert.assertTrue(actualNumber > number);
    }

    public void isNotLargerThen(Double number) {
        Assert.assertTrue(!(actualNumber > number));
    }
}
