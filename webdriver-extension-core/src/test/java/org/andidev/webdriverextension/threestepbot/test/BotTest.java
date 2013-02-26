package org.andidev.webdriverextension.threestepbot.test;

import org.andidev.webdriverextension.WebElement;
import org.andidev.webdriverextension.twostepbot2.Bot;
import org.junit.Test;

public class BotTest extends Bot {

    @Test
    public void testAssertThat() {
        Bot a = new Bot();
        WebElement loginButton = null;



        assertThat(loginButton).text().is("login.jsp");
    }
}
