package com.github.webdriverextensions.internal.generator;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.PackageElement;

public class ElementUtils {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ElementUtils.class);

    public static boolean isClass(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return element.getKind().equals(ElementKind.CLASS);
    }

    public static boolean isConstructor(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return element.getKind().equals(ElementKind.CONSTRUCTOR);
    }

    public static boolean isEnum(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return element.getKind().equals(ElementKind.ENUM);
    }

    public static boolean isField(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return element.getKind().equals(ElementKind.FIELD);
    }

    public static boolean isInterface(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return element.getKind().equals(ElementKind.INTERFACE);
    }

    public static boolean isMethod(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return element.getKind().equals(ElementKind.METHOD);
    }

    public static boolean isPackage(Element element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        return element.getKind().equals(ElementKind.PACKAGE);
    }

    public static String getPackageName(Element element) {
        if (!(isPackage(element) || isClass(element) || isInterface(element))) {
            throw new IllegalArgumentException("Element must be either a Package, Class or Interface");
        }
        if (isClass(element) || isInterface(element)) {
            return ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
        }
        return ((PackageElement) element).getQualifiedName().toString();
    }
}
