package com.github.webdriverextensions.internal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.lang.reflect.TypeVariable;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class ReflectionUtils {

    private ReflectionUtils() {}
    
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

    public static Class getType(Field field, ParameterizedType genericTypeArguments) {
        Type genericType = field.getGenericType();
        if (genericType instanceof TypeVariable) {
            TypeVariable genericTypeVariable = (TypeVariable) genericType;
            TypeVariable<?>[] classGenericTypeParameters = ((TypeVariable) genericType).getGenericDeclaration().getTypeParameters();
            for (int i = 0; i < classGenericTypeParameters.length; i++) {
                if (classGenericTypeParameters[i].getName().equals(genericTypeVariable.getName())) {
                    return (Class) genericTypeArguments.getActualTypeArguments()[i];
                }
            }
            throw new WebDriverExtensionException("Could not find genericTypeVariableName = " + genericTypeVariable.getName() + " in class");
        } else {
            return field.getType();
        }
    }

    public static Class getListType(Field field, ParameterizedType genericTypeArguments) {
        Type genericType = field.getGenericType();
        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

        if (listType instanceof TypeVariable) {
            String genericTypeVariableName = ((TypeVariable) listType).getName();
            TypeVariable<?>[] classGenericTypeParameters = ((TypeVariable) listType).getGenericDeclaration().getTypeParameters();
            for (int i = 0; i < classGenericTypeParameters.length; i++) {
                if (classGenericTypeParameters[i].getName().equals(genericTypeVariableName)) {
                    return (Class) genericTypeArguments.getActualTypeArguments()[i];
                }
            }
            throw new WebDriverExtensionException("Could not find genericTypeVariableName = " + genericTypeVariableName + " in class");
        } if (listType instanceof ParameterizedType) {
            return (Class) ((ParameterizedType) listType).getRawType();
        } else {
            return (Class) listType;
        }
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
