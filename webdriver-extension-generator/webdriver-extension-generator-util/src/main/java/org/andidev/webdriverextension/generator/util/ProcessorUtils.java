package org.andidev.webdriverextension.generator.util;

import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

public class ProcessorUtils {

    public static String getPackageName(TypeElement element) {
        return ((PackageElement) element.getEnclosingElement()).getQualifiedName().toString();
    }

    public static String getClassName(TypeElement element) {
        return element.getSimpleName().toString();
    }
}
