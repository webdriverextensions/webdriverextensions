package org.andidev.webdriverextension.internal;

import org.andidev.webdriverextension.internal.DefaultWebContainerFactory;
import static org.andidev.webdriverextension.internal.WebDriverExtensionAnnotations.getDelagate;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.andidev.webdriverextension.WebContainer;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.WebSite;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultFieldDecorator;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class WebDriverExtensionFieldDecorator extends DefaultFieldDecorator {

    private WebDriver driver;
    private SiteAndPageObjectPool pool;
    private WebContainerFactory webContainerFactory;
    private WebContainerListFactory webContainerListFactory;

    public WebDriverExtensionFieldDecorator(final SearchContext searchContext, final WebDriver driver) {
        super(new WebDriverExtensionElementLocatorFactory(searchContext, driver));
        this.driver = driver;
        this.pool = new SiteAndPageObjectPool(driver);
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
        if (isDecoratablePageObject(field)) {
            return pool.getPageObject(field, this);
        }
        if (isDecoratableSiteObject(field)) {
            return pool.getSiteObject(field, this);
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

    private Object decorateWebContainer(final ClassLoader loader, final Field field) {
        ElementLocator locator = factory.createLocator(field);
        Class type = (Class<? extends WebContainer>) field.getType();
        final WebElement webElement = proxyForLocator(loader, locator);
        final WebContainer webContainer = webContainerFactory.create(type, webElement);
        PageFactory.initElements(new WebDriverExtensionFieldDecorator(webElement, driver), webContainer);
        webContainer.delegateWebElement = getDelagate(webContainer);
        return webContainer;
    }

    private Object decorateWebContainerList(final ClassLoader loader, final Field field) {
        ElementLocator locator = factory.createLocator(field);
        Class listType = ReflectionUtils.getListType(field);
        List<WebElement> webElements = proxyForListLocator(loader, locator);
        final List<? extends WebContainer> webContainerList = webContainerListFactory.create(listType, webElements, driver);
        return webContainerList;
    }
}
