package org.andidev.webdriverextension.internal;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class BotUtilsTest {

    @Test
    public void noramlizeUrlTest() {
        String normalizedUrl = "http://www.google.com/this/is/it?test=123&jaja=abc";
        String url;

        url = BotUtils.normalizeUrl("HTTP://www.google.com/this/is/it?test=123&jaja=abc");

//        assertThat(url, is(normalizedUrl));

    }
}