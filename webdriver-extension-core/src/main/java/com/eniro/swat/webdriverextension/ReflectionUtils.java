package com.eniro.swat.webdriverextension;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class ReflectionUtils {

    public static By getBy(WebElement webElement) {
        return getBy(getLocator(webElement));
    }

    public static By getBy(ElementLocator locator) {
        try {
            Field byField = locator.getClass().getDeclaredField("by");
            byField.setAccessible(true);
            By by = (By) byField.get(locator);
            byField.setAccessible(false);
            return by;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static ElementLocator getLocator(WebElement webElement) {
        try {
            Field locatorField = webElement.getClass().getDeclaredField("locator");
            locatorField.setAccessible(true);
            ElementLocator locator = (ElementLocator) locatorField.get(locatorField);
            locatorField.setAccessible(false);
            return locator;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    public static Class<? extends HtmlTag> getListType(Field field) {
        Type genericType = field.getGenericType();
        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
        return (Class<? extends HtmlTag>) listType;
    }

    public static Field[] getDeclaredFields(Class clazz, boolean recursively) {
        List<Field> fields = new LinkedList<Field>();
        Field[] declaredFields = clazz.getDeclaredFields();
        Collections.addAll(fields, declaredFields);

        Class superClass = clazz.getSuperclass();

        if (superClass != null && recursively) {
            Field[] declaredFieldsOfSuper = getDeclaredFields(superClass, recursively);
            if (declaredFieldsOfSuper.length > 0) {
                Collections.addAll(fields, declaredFieldsOfSuper);
            }
        }

        return fields.toArray(new Field[fields.size()]);
    }

    public static Field[] getAnnotatedDeclaredFields(Class clazz,
            Class<? extends Annotation> annotationClass,
            boolean recursively) {
        Field[] allFields = getDeclaredFields(clazz, recursively);
        List<Field> annotatedFields = new LinkedList<Field>();

        for (Field field : allFields) {
            if (field.isAnnotationPresent(annotationClass)) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields.toArray(new Field[annotatedFields.size()]);
    }
}
