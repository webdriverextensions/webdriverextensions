package com.github.webdriverextensions.junitrunner;

import static com.github.webdriverextensions.Bot.assertCurrentUrlContains;
import static com.github.webdriverextensions.Bot.open;
import org.junit.Test;

public class InheritedRunnerTest extends InheritedRunner {

    @Test
    public void test() {
        open("http://www.google.com");
        assertCurrentUrlContains("google");
    }
}