package com.github.webdriverextensions;

import static com.github.webdriverextensions.Bot.assertTextEquals;
import static com.github.webdriverextensions.Bot.open;
import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(WebDriverRunner.class)
@Chrome
public class WebDriverExtensionsFieldDecoratorMultipleGenericsTest {

    public final String url = "file://" + getClass().getResource("/html/multiple-generics-test.html").getPath();

    public static class ClassWithTypeParameter<T extends WebComponent, S extends WebComponent, U> extends WebComponent {
        @FindBy(className = "firstTypeVariable")
        T firstTypeVariable;
        @FindBy(className = "secondTypeVariableInList")
        List<S> secondTypeVariableInList;
        U nonWebElement;
    }

    public static class FirstTypeArgument extends WebComponent {
        @FindBy(className = "firstTypeArgument")
        WebElement firstTypeArgument;
    }

    public static class SecondTypeArgument extends WebComponent {
        @FindBy(className = "secondTypeArgument")
        WebElement secondTypeArgument;
    }

    @FindBy(className = "typeArgument")
    ClassWithTypeParameter<FirstTypeArgument, SecondTypeArgument, Integer> typeArgument;
    @FindBy(className = "typeArgumentInList")
    List<ClassWithTypeParameter<FirstTypeArgument, SecondTypeArgument, Integer>> typeArgumentInList;

    @Test
    public void testPassingTypeArgumentsToTypeVariable(){
        open(url);
        assertTextEquals("typeArgument.firstTypeVariable.firstTypeArgument", typeArgument.firstTypeVariable.firstTypeArgument);
    }

    @Test
    public void testPassingTypeArgumentsToTypeVariableInList(){
        open(url);
        assertTextEquals("typeArgument.secondTypeVariableInList.get(0).secondTypeArgument", typeArgument.secondTypeVariableInList.get(0).secondTypeArgument);
        assertTextEquals("typeArgument.secondTypeVariableInList.get(1).secondTypeArgument", typeArgument.secondTypeVariableInList.get(1).secondTypeArgument);
    }

    @Test
    public void testPassingTypeArgumentsInListToTypeVariable(){
        open(url);
        assertTextEquals("typeArgumentInList.get(0).firstTypeVariable.firstTypeArgument", typeArgumentInList.get(0).firstTypeVariable.firstTypeArgument);
        assertTextEquals("typeArgumentInList.get(1).firstTypeVariable.firstTypeArgument", typeArgumentInList.get(1).firstTypeVariable.firstTypeArgument);
    }

    @Test
    public void testPassingTypeArgumentsInListToTypeVariableInList(){
        open(url);
        assertTextEquals("typeArgumentInList.get(0).secondTypeVariableInList.get(0).secondTypeArgument", typeArgumentInList.get(0).secondTypeVariableInList.get(0).secondTypeArgument);
        assertTextEquals("typeArgumentInList.get(1).secondTypeVariableInList.get(1).secondTypeArgument", typeArgumentInList.get(1).secondTypeVariableInList.get(1).secondTypeArgument);
        assertTextEquals("typeArgumentInList.get(0).secondTypeVariableInList.get(0).secondTypeArgument", typeArgumentInList.get(0).secondTypeVariableInList.get(0).secondTypeArgument);
        assertTextEquals("typeArgumentInList.get(1).secondTypeVariableInList.get(1).secondTypeArgument", typeArgumentInList.get(1).secondTypeVariableInList.get(1).secondTypeArgument);
    }

}
