package com.eniro.aspectjtest;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MainPageTest {

    @Test
    public void mainPageOpenTest() {
        MainPage main = new MainPage();
        main.open();
    }

    @Test
    public void mainPageNotOpenTest() {
        MainPage main = new MainPage();
        main.open();
    }
}
