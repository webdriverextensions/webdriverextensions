package org.andidev.webdriverextension.generator.util;

import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;

public class ElementUtils {

    public static String getPackageName(Element element) {
        return ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
    }

    public static String getClassName(Element element) {
        return element.getSimpleName().toString();
    }
}
