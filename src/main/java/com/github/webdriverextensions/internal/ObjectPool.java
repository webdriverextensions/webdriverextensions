package com.github.webdriverextensions.internal;

import com.github.webdriverextensions.WebDriverExtensionFieldDecorator;
import java.lang.reflect.Field;
import java.util.HashMap;
import com.github.webdriverextensions.WebPage;
import com.github.webdriverextensions.WebRepository;
import com.github.webdriverextensions.WebSite;
import static com.github.webdriverextensions.internal.utils.StringUtils.quote;
import java.lang.reflect.ParameterizedType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openqa.selenium.WebDriver;

public class ObjectPool {

    private final WebDriver driver;
    private final HashMap<Class<?>, WebSite> siteObjects = new HashMap<>();
    private final HashMap<ClassGeneric, WebPage> pageObjects = new HashMap<>();
    private final HashMap<ClassGeneric, WebRepository> repositoryObjects = new HashMap<>();

    public static class ClassGeneric {
        Class<?> clazz;
        ParameterizedType generics;

        @Override
        public int hashCode() {
            return new HashCodeBuilder()
                    .append(clazz)
                    .append(generics)
                    .toHashCode();
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof ClassGeneric) {
                final ClassGeneric other = (ClassGeneric) obj;
                return new EqualsBuilder()
                        .append(clazz, other.clazz)
                        .append(generics, other.generics)
                        .isEquals();
            } else {
                return false;
            }
        }
    }

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
        ClassGeneric key = new ClassGeneric();
        key.clazz = field.getType();
        if (field.getGenericType() instanceof ParameterizedType) {
            key.generics = (ParameterizedType)field.getGenericType();
        }
        WebPage pageObject = pageObjects.get(key);
        if (pageObject == null) {
            pageObject = createPageObject(field);
            pageObjects.put(key, pageObject);
            if (field.getGenericType() instanceof ParameterizedType) {
                decorator.setGenericTypeArguments((ParameterizedType) field.getGenericType());
            }
            pageObject.initElements(decorator);
        }
        return pageObject;
    }

    public WebRepository getRepositoryObject(Field field, WebDriverExtensionFieldDecorator decorator) {
        ClassGeneric key = new ClassGeneric();
        key.clazz = field.getType();
        if (field.getGenericType() instanceof ParameterizedType) {
            key.generics = (ParameterizedType)field.getGenericType();
        }
        WebRepository repositoryObject = repositoryObjects.get(key);
        if (repositoryObject == null) {
            repositoryObject = createRepositoryObject(field);
            repositoryObjects.put(key, repositoryObject);
            if (field.getGenericType() instanceof ParameterizedType) {
                decorator.setGenericTypeArguments((ParameterizedType) field.getGenericType());
            }
            repositoryObject.initElements(decorator);
        }
        return repositoryObject;
    }

    private WebSite createSiteObject(Field field) {
        WebSite siteObject;
        try {
            siteObject = (WebSite) field.getType().newInstance();
        } catch (InstantiationException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw new WebDriverExtensionException("Failed to instantiate WebSite class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since no args constructor is missing. Remove any other constructor or implement a no args constructor.", ex);
            } else {
                throw new WebDriverExtensionException("Failed to instantiate WebSite class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since class is abstract. Remove the abstract modifier and implement the abstract methods.", ex);
            }
        } catch (IllegalAccessException ex) {
            throw new WebDriverExtensionException("Failed to instantiate WebSite class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since constructor is not accessable. Make the constructor public.", ex);
        }
        return siteObject;
    }

    private WebPage createPageObject(Field field) {
        WebPage pageObject;
        try {
            pageObject = (WebPage) field.getType().newInstance();
        } catch (InstantiationException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw new WebDriverExtensionException("Failed to instantiate WebPage class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since no args constructor is missing. Remove any other constructor or implement a no args constructor.", ex);
            } else {
                throw new WebDriverExtensionException("Failed to instantiate WebPage class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since class is abstract. Remove the abstract modifier and implement the abstract methods.", ex);
            }
        } catch (IllegalAccessException ex) {
            throw new WebDriverExtensionException("Failed to instantiate WebPage class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since constructor is not accessable. Make the constructor public.", ex);
        }
        return pageObject;
    }

    private WebRepository createRepositoryObject(Field field) {
        WebRepository repositoryObject;
        try {
            repositoryObject = (WebRepository) field.getType().newInstance();
        } catch (InstantiationException ex) {
            if (ex.getCause() instanceof NoSuchMethodException) {
                throw new WebDriverExtensionException("Failed to instantiate WebRepository class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since no args constructor is missing. Remove any other constructor or implement a no args constructor.", ex);
            } else {
                throw new WebDriverExtensionException("Failed to instantiate WebRepository class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since class is abstract. Remove the abstract modifier and implement the abstract methods.", ex);
            }
        } catch (IllegalAccessException ex) {
            throw new WebDriverExtensionException("Failed to instantiate WebRepository class " + quote(field.getType().getSimpleName()) + " for field " + quote(field.getName()) + " since constructor is not accessable. Make the constructor public.", ex);
        }
        return repositoryObject;
    }

}
