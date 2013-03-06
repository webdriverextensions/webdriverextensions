package org.andidev.webdriverextension.area51;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.lift.HamcrestWebDriverTestCase;

//import static org.openqa.selenium.lift.Finders.*;
//import static org.openqa.selenium.lift.Matchers.*;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

public class Hamcrest extends HamcrestWebDriverTestCase {

    @Override
    protected WebDriver createDriver() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void testHasAnImageSearchPage() throws Exception {

        goTo("http://www.google.com");

//        assertPresenceOf(link("Images"));
//        assertPresenceOf(startsWith(null)atLeast(4), links().with(text(not(equalTo("Images")))));
//
//        clickOn(link("Images"));
//
//        assertThat(textIn(), );
//
//        assertPresenceOf(title().with(text(equalTo("Google Image Search"))));
    }
}
