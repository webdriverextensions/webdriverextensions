package org.andidev.webdriverextension;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.andidev.webdriverextension.annotation.Delegate;
import org.andidev.webdriverextension.annotation.ResetSearchContext;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class DefaultWebElementFieldDecorator extends DefaultFieldDecorator {

    private WebDriver driver;
    ElementLocatorFactory driverFactory;
    private WebElementFactory htmlTagFactory;
    private WebElementListFactory htmlTagListFactory;

    public DefaultWebElementFieldDecorator(final WebDriver driver) {
        this(driver, driver);
    }

    public DefaultWebElementFieldDecorator(final SearchContext searchContext, final WebDriver driver) {
        super(new DefaultElementLocatorFactory(searchContext));
        this.driver = driver;
        this.driverFactory = new DefaultElementLocatorFactory(driver);
        this.htmlTagFactory = new DefaultWebElementFactory();
        this.htmlTagListFactory = new DefaultWebElementListFactory(htmlTagFactory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (isDecoratableHtmlTag(field)) {
            return decorateHtmlTag(loader, field);
        }
        if (isDecoratableHtmlTagList(field)) {
            return decorateHtmlTagList(loader, field);
        }
        return null;
    }

    private boolean isDecoratableHtmlTag(Field field) {
        if (!WebElement.class.isAssignableFrom(field.getType())) {
            return false;
        }

        return true;
    }

    private boolean isDecoratableHtmlTagList(Field field) {
        if (!List.class.isAssignableFrom(field.getType())) {
            return false;
        }

        // Type erasure in Java isn't complete. Attempt to discover the generic
        // type of the list.
        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType)) {
            return false;
        }

        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

//        if (!WebElement.class.equals(listType)) {
//            return false;
//        }

        if (!WebElement.class.isAssignableFrom((Class) listType)) {
            return false;
        }

        if (field.getAnnotation(FindBy.class) == null
                && field.getAnnotation(FindBys.class) == null) {
            return false;
        }

        return true;
    }

    private Object decorateHtmlTag(final ClassLoader loader, final Field field) {
        ElementLocator locator = createLocator(field);
        Class type = (Class<? extends WebElement>) field.getType();
        final org.openqa.selenium.WebElement webElement = proxyForLocator(loader, locator);
        final By by = ReflectionUtils.getBy(locator);
        final WebElement htmlTag = htmlTagFactory.create(type, webElement, by);
        if (hasAnnotatedResetSearchContext(field)) {
            PageFactory.initElements(new DefaultWebElementFieldDecorator(driver), htmlTag);
        } else {
            PageFactory.initElements(new DefaultWebElementFieldDecorator(webElement, driver), htmlTag);
        }
        htmlTag.delegateWebElement = getDelagate(htmlTag);
        return htmlTag;
    }

    private Object decorateHtmlTagList(final ClassLoader loader, final Field field) {
        ElementLocator locator = createLocator(field);
        Class listType = ReflectionUtils.getListType(field);
        List<org.openqa.selenium.WebElement> webElements = proxyForListLocator(loader, locator);
        final By by = ReflectionUtils.getBy(locator);
        final List<? extends WebElement> htmlTagList = htmlTagListFactory.create(listType, webElements, by, driver);
        return htmlTagList;
    }

    private ElementLocator createLocator(final Field field) {
        if (hasAnnotatedResetSearchContext(field)) {
            return driverFactory.createLocator(field);
        } else {
            return factory.createLocator(field);
        }
    }

    private boolean hasAnnotatedResetSearchContext(Field field) {
        ResetSearchContext annotation = (ResetSearchContext) field.getAnnotation(ResetSearchContext.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }

    private boolean hasAnnotatedDelegate(Field field) {
        Delegate annotation = (Delegate) field.getAnnotation(Delegate.class);
        if (annotation != null) {
            return true;
        }
        return false;
    }

    private org.openqa.selenium.WebElement getDelagate(WebElement htmlTag) {
        Field[] fields = ReflectionUtils.getAnnotatedDeclaredFields(htmlTag.getClass(), Delegate.class, true);
        if (fields.length == 0) {
            return null;
        }
        if (fields.length > 1) {
            throw new RuntimeException("More than one @Delagate annotation used. There should only exist one.");
        }
        org.openqa.selenium.WebElement delegate;
        try {
            delegate = (org.openqa.selenium.WebElement) fields[0].get(htmlTag);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return delegate;
    }
}
