package org.andidev.webdriverextension.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.andidev.webdriverextension.WebComponent;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class ReflectionUtils {

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

    public static Class<? extends WebComponent> getListType(Field field) {
        Type genericType = field.getGenericType();
        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];
        return (Class<? extends WebComponent>) listType;
    }

    public static Field[] getAnnotatedDeclaredFields(Class clazz,
            Class<? extends Annotation> annotationClass) {
        Field[] allFields = getDeclaredFields(clazz);
        List<Field> annotatedFields = new LinkedList<Field>();

        for (Field field : allFields) {
            if (field.isAnnotationPresent(annotationClass)) {
                annotatedFields.add(field);
            }
        }

        return annotatedFields.toArray(new Field[annotatedFields.size()]);
    }

    public static Field[] getDeclaredFields(Class clazz) {
        List<Field> fields = new LinkedList<Field>();
        Field[] declaredFields = clazz.getDeclaredFields();
        Collections.addAll(fields, declaredFields);

        Class superClass = clazz.getSuperclass();

        if (superClass != null) {
            Field[] declaredFieldsOfSuper = getDeclaredFields(superClass);
            if (declaredFieldsOfSuper.length > 0) {
                Collections.addAll(fields, declaredFieldsOfSuper);
            }
        }

        return fields.toArray(new Field[fields.size()]);
    }
}