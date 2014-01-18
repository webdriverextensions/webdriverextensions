package com.github.webdriverextensions.internal.junitrunner;

/*
 * Copyright 2002-2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * General utility methods for working with annotations.
 *
 * @author Rob Harrop
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Mark Fisher
 * @since 2.0
 * @see java.lang.reflect.Method#getAnnotations()
 * @see java.lang.reflect.Method#getAnnotation(Class)
 */
public abstract class AnnotationUtils {

    /** The attribute name for annotations with a single element */
    static final String VALUE = "value";

    private static final Map<Class, Boolean> annotatedInterfaceCache = new WeakHashMap<Class, Boolean>();


    private static boolean isInterfaceWithAnnotatedMethods(Class<?> iface) {
        synchronized (annotatedInterfaceCache) {
            Boolean flag = annotatedInterfaceCache.get(iface);
            if (flag != null) {
                return flag;
            }
            boolean found = false;
            for (Method ifcMethod : iface.getMethods()) {
                if (ifcMethod.getAnnotations().length > 0) {
                    found = true;
                    break;
                }
            }
            annotatedInterfaceCache.put(iface, found);
            return found;
        }
    }


    /**
     * Retrieve the given annotation's attributes as a Map, preserving all attribute types as-is.
     * @param annotation the annotation to retrieve the attributes for
     * @return the Map of annotation attributes, with attribute names as keys and
     * corresponding attribute values as values
     */
    public static Map<String, Object> getAnnotationAttributes(Annotation annotation) {
        return getAnnotationAttributes(annotation, false);
    }

    /**
     * Retrieve the given annotation's attributes as a Map.
     * @param annotation the annotation to retrieve the attributes for
     * @param classValuesAsString whether to turn Class references into Strings (for compatibility with
     * {@link org.springframework.core.type.AnnotationMetadata} or to preserve them as Class references
     * @return the Map of annotation attributes, with attribute names as keys and
     * corresponding attribute values as values
     */
    public static Map<String, Object> getAnnotationAttributes(Annotation annotation, boolean classValuesAsString) {
        Map<String, Object> attrs = new HashMap<String, Object>();
        Method[] methods = annotation.annotationType().getDeclaredMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == 0 && method.getReturnType() != void.class) {
                try {
                    Object value = method.invoke(annotation);
                    if (classValuesAsString) {
                        if (value instanceof Class) {
                            value = ((Class) value).getName();
                        }
                        else if (value instanceof Class[]) {
                            Class[] clazzArray = (Class[]) value;
                            String[] newValue = new String[clazzArray.length];
                            for (int i = 0; i < clazzArray.length; i++) {
                                newValue[i] = clazzArray[i].getName();
                            }
                            value = newValue;
                        }
                    }
                    attrs.put(method.getName(), value);
                }
                catch (Exception ex) {
                    throw new IllegalStateException("Could not obtain annotation attribute values", ex);
                }
            }
        }
        return attrs;
    }

    /**
     * Retrieve the <em>value</em> of the <code>&quot;value&quot;</code> attribute of a
     * single-element Annotation, given an annotation instance.
     * @param annotation the annotation instance from which to retrieve the value
     * @return the attribute value, or <code>null</code> if not found
     * @see #getValue(Annotation, String)
     */
    public static Object getValue(Annotation annotation) {
        return getValue(annotation, VALUE);
    }

    /**
     * Retrieve the <em>value</em> of a named Annotation attribute, given an annotation instance.
     * @param annotation the annotation instance from which to retrieve the value
     * @param attributeName the name of the attribute value to retrieve
     * @return the attribute value, or <code>null</code> if not found
     * @see #getValue(Annotation)
     */
    public static Object getValue(Annotation annotation, String attributeName) {
        try {
            Method method = annotation.annotationType().getDeclaredMethod(attributeName, new Class[0]);
            return method.invoke(annotation);
        }
        catch (Exception ex) {
            return null;
        }
    }

    /**
     * Retrieve the <em>default value</em> of the <code>&quot;value&quot;</code> attribute
     * of a single-element Annotation, given an annotation instance.
     * @param annotation the annotation instance from which to retrieve the default value
     * @return the default value, or <code>null</code> if not found
     * @see #getDefaultValue(Annotation, String)
     */
    public static Object getDefaultValue(Annotation annotation) {
        return getDefaultValue(annotation, VALUE);
    }

    /**
     * Retrieve the <em>default value</em> of a named Annotation attribute, given an annotation instance.
     * @param annotation the annotation instance from which to retrieve the default value
     * @param attributeName the name of the attribute value to retrieve
     * @return the default value of the named attribute, or <code>null</code> if not found
     * @see #getDefaultValue(Class, String)
     */
    public static Object getDefaultValue(Annotation annotation, String attributeName) {
        return getDefaultValue(annotation.annotationType(), attributeName);
    }

    /**
     * Retrieve the <em>default value</em> of the <code>&quot;value&quot;</code> attribute
     * of a single-element Annotation, given the {@link Class annotation type}.
     * @param annotationType the <em>annotation type</em> for which the default value should be retrieved
     * @return the default value, or <code>null</code> if not found
     * @see #getDefaultValue(Class, String)
     */
    public static Object getDefaultValue(Class<? extends Annotation> annotationType) {
        return getDefaultValue(annotationType, VALUE);
    }

    /**
     * Retrieve the <em>default value</em> of a named Annotation attribute, given the {@link Class annotation type}.
     * @param annotationType the <em>annotation type</em> for which the default value should be retrieved
     * @param attributeName the name of the attribute value to retrieve.
     * @return the default value of the named attribute, or <code>null</code> if not found
     * @see #getDefaultValue(Annotation, String)
     */
    public static Object getDefaultValue(Class<? extends Annotation> annotationType, String attributeName) {
        try {
            Method method = annotationType.getDeclaredMethod(attributeName, new Class[0]);
            return method.getDefaultValue();
        }
        catch (Exception ex) {
            return null;
        }
    }

}
