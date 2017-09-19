package com.github.webdriverextensions;



import org.junit.runner.RunWith;

import com.github.webdriverextensions.generator.WebRepositoryGenerator;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;

@RunWith(WebDriverRunner.class)
@Chrome
public class EmptyTest extends WebRepositoryGenerator {

}
