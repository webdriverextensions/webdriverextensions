package org.andidev.webdriverextension.twostepbot.test;

import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.twostepbot.Bot;
import org.junit.Test;

public class BotTest extends Bot {

    @Test
    public void testAssertThat() {
        Bot a = new Bot();
        WebElement loginButton = null;



        assertTextIn(loginButton).is("Login");
    }
}
