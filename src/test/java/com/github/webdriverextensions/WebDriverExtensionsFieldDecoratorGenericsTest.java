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
public class WebDriverExtensionsFieldDecoratorGenericsTest {

    public final String url = "file://" + getClass().getResource("/html/generics-test.html").getPath();

    public static class ClassWithTypeParameter<T extends WebComponent> extends WebComponent {
        @FindBy(className = "typeVariable")
        T typeVariable;
        @FindBy(className = "typeVariableInList")
        List<T> typeVariableInList;
    }

    public static class TypeArgument extends WebComponent {
        @FindBy(className = "text")
        WebElement text;
    }

    @FindBy(className = "typeArgument")
    ClassWithTypeParameter<TypeArgument> typeArgument;
    @FindBy(className = "typeArgumentInList")
    List<ClassWithTypeParameter<TypeArgument>> typeArgumentInList;

    public static class PageWithTypeParameter<T extends WebComponent> extends WebPage {
        @FindBy(className = "typeVariable")
        T typeVariable;
        @FindBy(className = "typeVariableInList")
        List<T> typeVariableInList;
        @Override public void open(Object... arguments) {}
        @Override public void assertIsOpen(Object... arguments) throws AssertionError {}
    }
    public static class TypeArgument2 extends WebComponent {
        @FindBy(className = "text")
        WebElement text;
    }
    PageWithTypeParameter<TypeArgument2> pageWithTypeArgument;

    public static class RepositoryWithTypeParameter<T extends WebComponent> extends WebRepository {
        @FindBy(className = "typeVariable")
        T typeVariable;
        @FindBy(className = "typeVariableInList")
        List<T> typeVariableInList;
    }
    public static class TypeArgument3 extends WebComponent {
        @FindBy(className = "text")
        WebElement text;
    }
    RepositoryWithTypeParameter<TypeArgument3> repositoryWithTypeArgument;

    @Test
    public void testPassingTypeArgumentToTypeVariable(){
        open(url);
        assertTextEquals("typeArgument.typeVariable.text", typeArgument.typeVariable.text);
    }

    @Test
    public void testPassingTypeArgumentToTypeVariableInList(){
        open(url);
        assertTextEquals("typeArgument.typeVariableInList.get(0).text", typeArgument.typeVariableInList.get(0).text);
        assertTextEquals("typeArgument.typeVariableInList.get(1).text", typeArgument.typeVariableInList.get(1).text);
    }

    @Test
    public void testPassingTypeArgumentInListToTypeVariable(){
        open(url);
        assertTextEquals("typeArgumentInList.get(0).typeVariable.text", typeArgumentInList.get(0).typeVariable.text);
        assertTextEquals("typeArgumentInList.get(1).typeVariable.text", typeArgumentInList.get(1).typeVariable.text);
    }

    @Test
    public void testPassingTypeArgumentInListToTypeVariableInList(){
        open(url);
        assertTextEquals("typeArgumentInList.get(0).typeVariableInList.get(0).text", typeArgumentInList.get(0).typeVariableInList.get(0).text);
        assertTextEquals("typeArgumentInList.get(1).typeVariableInList.get(1).text", typeArgumentInList.get(1).typeVariableInList.get(1).text);
        assertTextEquals("typeArgumentInList.get(0).typeVariableInList.get(0).text", typeArgumentInList.get(0).typeVariableInList.get(0).text);
        assertTextEquals("typeArgumentInList.get(1).typeVariableInList.get(1).text", typeArgumentInList.get(1).typeVariableInList.get(1).text);
    }

    @Test
    public void testPassingTypeArgumentToPageWithTypeParameter(){
        open(url);
        assertTextEquals("typeArgument.typeVariable.text", pageWithTypeArgument.typeVariable.text);
    }

    @Test
    public void testPassingTypeArgumentToRepositoryWithTypeParameter(){
        open(url);
        assertTextEquals("typeArgument.typeVariable.text", repositoryWithTypeArgument.typeVariable.text);
    }
}
