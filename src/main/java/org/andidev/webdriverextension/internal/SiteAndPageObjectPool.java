package org.andidev.webdriverextension.internal;

import org.andidev.webdriverextension.WebDriverExtensionFieldDecorator;
import com.google.common.base.Objects;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.WebRepository;
import org.andidev.webdriverextension.WebSite;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.openqa.selenium.WebDriver;

public class SiteAndPageObjectPool {

    WebDriver driver;
    HashMap<Key, WebSite> siteObjects;
    HashMap<Key, WebPage> pageObjects;
    HashMap<Key, WebRepository> repositoryObjects;

    public SiteAndPageObjectPool(WebDriver driver) {
        this.driver = driver;
        this.siteObjects = new HashMap<Key, WebSite>();
        this.pageObjects = new HashMap<Key, WebPage>();
        this.repositoryObjects = new HashMap<Key, WebRepository>();
    }

    public WebSite getSiteObject(Field field, WebDriverExtensionFieldDecorator decorator) {
        Key siteObjectKey = Key.createSiteObjectKey(field);
        WebSite siteObject = siteObjects.get(siteObjectKey);
        if (siteObject == null) {
            siteObject = createSiteObject(field);
            siteObjects.put(siteObjectKey, siteObject);
            siteObject.initElements(driver, decorator);
        }
        return siteObject;
    }

    public WebPage getPageObject(Field field, WebDriverExtensionFieldDecorator decorator) {
        Key pageObjectKey = Key.createPageObjectKey(field);
        WebPage pageObject = pageObjects.get(pageObjectKey);
        if (pageObject == null) {
            pageObject = createPageObject(field);
            pageObjects.put(pageObjectKey, pageObject);
            pageObject.initElements(driver);
        }
        Key siteObjectKey = Key.createSiteObjectKeyFromPageObjectField(field);
        WebSite siteObject = siteObjects.get(siteObjectKey);
        if (siteObject == null) {
            siteObject = createSiteObjectFromPageObjectField(field);
            siteObjects.put(siteObjectKey, siteObject);
            siteObject.initElements(driver, decorator);
        }
        pageObject.site = siteObject;
        return pageObject;
    }

    public WebRepository getRepositoryObject(Field field, WebDriverExtensionFieldDecorator decorator) {
        Key repositoryObjectKey = Key.createRepositoryObjectKey(field);
        WebRepository repositoryObject = repositoryObjects.get(repositoryObjectKey);
        if (repositoryObject == null) {
            repositoryObject = createRepositoryObject(field);
            repositoryObjects.put(repositoryObjectKey, repositoryObject);
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

    private WebSite createSiteObjectFromPageObjectField(Field field) {
        WebSite siteObject;
        try {
            Type siteObjectType = getSiteObjectTypeFromGenerics(field.getType());
            siteObject = (WebSite) ((Class) siteObjectType).newInstance();
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

    public static class Key {

        private final String name;
        private final Type objectType;

        public Key(String name, Type genericType) {
            this.name = name;
            this.objectType = genericType;
        }

        private static Key createSiteObjectKey(Field field) {
            if (WebSite.class.isAssignableFrom(field.getType())) {
                Type siteObjectType = field.getType();
                String name = field.getName();
                return new Key(name, siteObjectType);
            }
            throw new WebDriverExtensionException();
        }

        private static Key createSiteObjectKeyFromPageObjectField(Field field) {
            if (WebPage.class.isAssignableFrom(field.getType())) {
                Type siteObjectType = getSiteObjectTypeFromGenerics(field.getType());
                String name = StringUtils.uncapitalize(((Class) siteObjectType).getName());
                return new Key(name, siteObjectType);
            }
            throw new WebDriverExtensionException();
        }

        private static Key createPageObjectKey(Field field) {
            if (WebPage.class.isAssignableFrom(field.getType())) {
                Type siteObjectType = getSiteObjectTypeFromGenerics(field.getType());
                String name = field.getName();
                return new Key(name, siteObjectType);
            }
            throw new WebDriverExtensionException();
        }

        private static Key createRepositoryObjectKey(Field field) {
            if (WebRepository.class.isAssignableFrom(field.getType())) {
                Type repositoryObjectType = field.getType();
                String name = field.getName();
                return new Key(name, repositoryObjectType);
            }
            throw new WebDriverExtensionException();
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Key) {
                final Key other = (Key) o;
                return Objects.equal(name, other.name)
                        && Objects.equal(objectType, other.objectType);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(name, objectType);
        }
    }

    private static Type getSiteObjectTypeFromGenerics(Class type) {
        if (type.getGenericSuperclass() instanceof Class) {
            return getSiteObjectTypeFromGenerics((Class) type.getGenericSuperclass());
        } else {
            ParameterizedType superClass = (ParameterizedType) type.getGenericSuperclass();
            if (superClass.getRawType().equals(WebPage.class)) {
                return superClass.getActualTypeArguments()[0];
            } else {
                throw new WebDriverExtensionException("Something went wrong! superClass = " + ReflectionToStringBuilder.toString(superClass, ToStringStyle.MULTI_LINE_STYLE));
            }
        }
    }
}
