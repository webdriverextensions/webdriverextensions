package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import com.google.common.base.Objects;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import com.github.webdriverextensions.WebPage;
import com.github.webdriverextensions.WebRepository;
import com.github.webdriverextensions.WebSite;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openqa.selenium.WebDriver;

public class ObjectPool {

    private final WebDriver driver;
    private final HashMap<Class<?>, WebSite> siteObjects = new HashMap<Class<?>, WebSite>();;
    private final HashMap<Class<?>, WebPage> pageObjects = new HashMap<Class<?>, WebPage>();;
    private final HashMap<Class<?>, WebRepository> repositoryObjects = new HashMap<Class<?>, WebRepository>();;

    public ObjectPool(WebDriver driver) {
        this.driver = driver;
    }

    public WebSite getSiteObject(Field field, WebDriverExtensionFieldDecorator decorator) {
        WebSite siteObject = siteObjects.get(field.getType());
        if (siteObject == null) {
            siteObject = createSiteObject(field);
            siteObjects.put(field.getType(), siteObject);
            siteObject.initElements(decorator);
        }
        return siteObject;
    }

    public WebPage getPageObject(Field field, WebDriverExtensionFieldDecorator decorator) {
        WebPage pageObject = pageObjects.get(field.getType());
        if (pageObject == null) {
            pageObject = createPageObject(field);
            pageObjects.put(field.getType(), pageObject);
            pageObject.initElements(decorator);
        }
        return pageObject;
    }

    public WebRepository getRepositoryObject(Field field, WebDriverExtensionFieldDecorator decorator) {
        WebRepository repositoryObject = repositoryObjects.get(field.getType());
        if (repositoryObject == null) {
            repositoryObject = createRepositoryObject(field);
            repositoryObjects.put(field.getType(), repositoryObject);
            repositoryObject.initElements(decorator);
        }
        return repositoryObject;
    }

    private WebSite createSiteObject(Field field) {
        WebSite siteObject;
        try {
            siteObject = (WebSite) field.getType().newInstance();
        } catch (InstantiationException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (IllegalAccessException ex) {
            throw new WebDriverExtensionException(ex);
        }
        return siteObject;
    }

    private WebPage createPageObject(Field field) {
        WebPage pageObject;
        try {
            pageObject = (WebPage) field.getType().newInstance();
        } catch (InstantiationException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (IllegalAccessException ex) {
            throw new WebDriverExtensionException(ex);
        }
        return pageObject;
    }

    private WebRepository createRepositoryObject(Field field) {
        WebRepository repositoryObject;
        try {
            repositoryObject = (WebRepository) field.getType().newInstance();
        } catch (InstantiationException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (IllegalAccessException ex) {
            throw new WebDriverExtensionException(ex);
        }
        return repositoryObject;
    }

}
