package com.github.webdriverextensions;

import com.github.webdriverextensions.internal.WebDriverExtensionAnnotations;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import com.github.webdriverextensions.internal.DefaultWebComponentFactory;
import com.github.webdriverextensions.internal.DefaultWebComponentListFactory;
import com.github.webdriverextensions.internal.ReflectionUtils;
import com.github.webdriverextensions.internal.SiteAndPageObjectPool;
import com.github.webdriverextensions.internal.WebComponentFactory;
import com.github.webdriverextensions.internal.WebComponentListFactory;
import com.github.webdriverextensions.internal.WebDriverExtensionElementLocatorFactory;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class WebDriverExtensionFieldDecorator extends DefaultFieldDecorator {

    private final WebDriver driver;
    private final SiteAndPageObjectPool pool;
    private final WebComponentFactory webComponentFactory;
    private final WebComponentListFactory webComponentListFactory;

    public WebDriverExtensionFieldDecorator(final WebDriver driver) {
        super(new WebDriverExtensionElementLocatorFactory(driver, driver));
        this.driver = driver;
        this.pool = new SiteAndPageObjectPool(driver);
        this.webComponentFactory = new DefaultWebComponentFactory();
        this.webComponentListFactory = new DefaultWebComponentListFactory(webComponentFactory);
    }

    public WebDriverExtensionFieldDecorator(final SearchContext searchContext, final WebDriver driver) {
        super(new WebDriverExtensionElementLocatorFactory(searchContext, driver));
        this.driver = driver;
        this.pool = new SiteAndPageObjectPool(driver);
        this.webComponentFactory = new DefaultWebComponentFactory();
        this.webComponentListFactory = new DefaultWebComponentListFactory(webComponentFactory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (isDecoratableWebComponent(field)) {
            return decorateWebComponent(loader, field);
        }
        if (isDecoratableWebComponentList(field)) {
            return decorateWebComponentList(loader, field);
        }
        if (isDecoratableSiteObject(field)) {
            return pool.getSiteObject(field, this);
        }
        if (isDecoratablePageObject(field)) {
            return pool.getPageObject(field, this);
        }
        if (isDecoratableRepositoryObject(field)) {
            return pool.getRepositoryObject(field, this);
        }
        if ("wrappedWebElement".equals(field.getName())) {
            return null;
        }
        if ("delegateWebElement".equals(field.getName())) {
            return null;
        }
        return super.decorate(loader, field);
    }

    private boolean isDecoratableWebComponent(Field field) {
        if (!WebComponent.class.isAssignableFrom(field.getType())) {
            return false;
        }

        return true;
    }

    private boolean isDecoratableWebComponentList(Field field) {
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

        if (!WebComponent.class.isAssignableFrom((Class) listType)) {
            return false;
        }

        if (field.getAnnotation(FindBy.class) == null
                && field.getAnnotation(FindBys.class) == null) {
            return false;
        }

        return true;
    }

    private boolean isDecoratablePageObject(Field field) {
        if (!WebPage.class.isAssignableFrom(field.getType())) {
            return false;
        }

        return true;
    }

    private boolean isDecoratableSiteObject(Field field) {
        if (!WebSite.class.isAssignableFrom(field.getType())) {
            return false;
        }

        return true;
    }

    private boolean isDecoratableRepositoryObject(Field field) {
        if (!WebRepository.class.isAssignableFrom(field.getType())) {
            return false;
        }

        return true;
    }

    private Object decorateWebComponent(final ClassLoader loader, final Field field) {
        ElementLocator locator = factory.createLocator(field);
        Class type = (Class<? extends WebComponent>) field.getType();
        final WebElement webElement = proxyForLocator(loader, locator);
        final WebComponent webComponent = webComponentFactory.create(type, webElement);
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(webElement, driver), webComponent);
        webComponent.delegateWebElement = WebDriverExtensionAnnotations.getDelagate(webComponent);
        return webComponent;
    }

    private Object decorateWebComponentList(final ClassLoader loader, final Field field) {
        ElementLocator locator = factory.createLocator(field);
        Class listType = ReflectionUtils.getListType(field);
        List<WebElement> webElements = proxyForListLocator(loader, locator);
        final List<? extends WebComponent> webComponentList = webComponentListFactory.create(listType, webElements, driver);
        return webComponentList;
    }
}
