package com.github.webdriverextensions.internal.utils;

import com.github.webdriverextensions.internal.WebDriverExtensionException;
import java.lang.reflect.InvocationTargetException;

public class InstanceUtils {

    private InstanceUtils() {}
    
    public static <T> T newInstance(Class clazz, Class<T> returnedClazz) {
        try {
            return (T) clazz.getConstructor().newInstance();
        } catch (NoSuchMethodException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (SecurityException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (InstantiationException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (IllegalAccessException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (IllegalArgumentException ex) {
            throw new WebDriverExtensionException(ex);
        } catch (InvocationTargetException ex) {
            throw new WebDriverExtensionException(ex);
        }
    }

}
