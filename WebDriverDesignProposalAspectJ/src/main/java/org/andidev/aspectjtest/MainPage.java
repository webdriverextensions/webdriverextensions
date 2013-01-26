package org.andidev.aspectjtest;

import org.junit.Assert;

public class MainPage implements Openable {

    public boolean open = false;
    
    public void open() {
        System.out.println("click(mainPageLink)");
    }

    public void isOpen() throws Error {
        System.out.println("assertIsDisplayed(mainPageLink)");
        Assert.fail();
    }
}
