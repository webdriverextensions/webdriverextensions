package com.github.webdriverextensions;

import com.github.webdriverextensions.junitrunner.WebDriverRunner;
import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.FindBy;

@RunWith(WebDriverRunner.class)
@Chrome
public class ObjectPoolTest {

    public static class Page extends WebPage {
        @Override public void open(Object... arguments) {}
        @Override public void assertIsOpen(Object... arguments) throws AssertionError {}
    }
    Page firstPageObject;
    Page secondPageObject;

    @Test
    public void testThatPageObjectsAreSingletons(){
        assert firstPageObject.equals(secondPageObject);
    }

    public static class PageWithTypeParameters<T extends WebComponent, U extends WebComponent> extends WebPage {
        @FindBy(className = "typeVariable")
        T typeVariable;
        @FindBy(className = "typeVariableInList")
        List<U> typeVariableInList;
        @Override public void open(Object... arguments) {}
        @Override public void assertIsOpen(Object... arguments) throws AssertionError {}
    }
    public static class TypeArgument extends WebComponent {}
    public static class TypeArgument2 extends WebComponent {}
    PageWithTypeParameters<TypeArgument, TypeArgument2> firstPageObjectWithTypeParameters;
    PageWithTypeParameters<TypeArgument, TypeArgument2> secondPageObjectWithTypeParameters;

    @Test
    public void testThatPageObjectsWithTypeParameterAreSingletons(){
        assert firstPageObjectWithTypeParameters.equals(secondPageObjectWithTypeParameters);
    }

    PageWithTypeParameters<TypeArgument2, TypeArgument> differentPageObjectWithTypeParameters;
    @Test
    public void testThatPageObjectsWithDifferentTypeParameterArePossibleToAddToPool(){
        assert !firstPageObjectWithTypeParameters.equals(differentPageObjectWithTypeParameters);
    }
}
