package org.andidev.webdriverextension;

import org.andidev.webdriverextension.utils.ReflectionUtils;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.andidev.webdriverextension.annotation.Delegate;
import org.andidev.webdriverextension.annotation.ResetSearchContext;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;

public class WebDriverExtensionFieldDecorator extends DefaultFieldDecorator {

    private WebDriver driver;
    ElementLocatorFactory driverFactory;
    private WebContainerFactory webContainerFactory;
    private WebContainerListFactory webContainerListFactory;

    public WebDriverExtensionFieldDecorator(final WebDriver driver) {
        this(driver, driver);
    }

    public WebDriverExtensionFieldDecorator(final SearchContext searchContext, final WebDriver driver) {
        super(new WebDriverExtensionElementLocatorFactory(searchContext, driver));
        this.driver = driver;
        this.webContainerFactory = new DefaultWebContainerFactory();
        this.webContainerListFactory = new DefaultWebContainerListFactory(webContainerFactory);
    }

    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (isDecoratableWebContainer(field)) {
            return decorateWebContainer(loader, field);
        }
        if (isDecoratableWebContainerList(field)) {
            return decorateWebContainerList(loader, field);
        }
        if ("wrappedWebElement".equals(field.getName())) {
            return null;
        }
        if ("delegateWebElement".equals(field.getName())) {
            return null;
        }
        return super.decorate(loader, field);
    }

    private boolean isDecoratableWebContainer(Field field) {
        if (!WebContainer.class.isAssignableFrom(field.getType())) {
            return false;
        }

        return true;
    }

    private boolean isDecoratableWebContainerList(Field field) {
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

        if (!WebContainer.class.isAssignableFrom((Class) listType)) {
            return false;
        }

        if (field.getAnnotation(FindBy.class) == null
                && field.getAnnotation(FindBys.class) == null) {
            return false;
        }

        return true;
    }

    private Object decorateWebContainer(final ClassLoader loader, final Field field) {
        ElementLocator locator = createLocator(field);
        Class type = (Class<? extends WebContainer>) field.getType();
        final WebElement webElement = proxyForLocator(loader, locator);
        final WebContainer webContainer = webContainerFactory.create(type, webElement);
        if (hasAnnotatedResetSearchContext(field)) {
            PageFactory.initElements(new WebDriverExtensionFieldDecorator(driver, driver), webContainer);
        } else {
            PageFactory.initElements(new WebDriverExtensionFieldDecorator(webElement, driver), webContainer);
        }
        webContainer.delegateWebElement = getDelagate(webContainer);
        return webContainer;
    }

    private Object decorateWebContainerList(final ClassLoader loader, final Field field) {
        ElementLocator locator = createLocator(field);
        Class listType = ReflectionUtils.getListType(field);
        List<WebElement> webElements = proxyForListLocator(loader, locator);
        final List<? extends WebContainer> webContainerList = webContainerListFactory.create(listType, webElements, driver);
        return webContainerList;
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

    private WebElement getDelagate(WebContainer webContainer) {
        Field[] fields = ReflectionUtils.getAnnotatedDeclaredFields(webContainer.getClass(), Delegate.class);
        if (fields.length == 0) {
            return null;
        }
        if (fields.length > 1) {
            throw new RuntimeException("More than one @Delagate annotation used. There should only exist one.");
        }
        WebElement delegate;
        try {
            delegate = (WebElement) fields[0].get(webContainer);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return delegate;
    }
}
