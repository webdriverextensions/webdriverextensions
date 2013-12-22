package org.webdriverextensions.internal;

import static com.google.common.base.Preconditions.*;
import javax.lang.model.element.Element;
import org.webdriverextensions.WebPage;
import org.webdriverextensions.generator.annotations.Generate;
import org.apache.commons.lang3.StringUtils;

public class GeneratorUtils {

    static public String getName(Class<WebPage> clazz) {
        checkNotNull(clazz);

        String annotationName = ((Generate) clazz.getAnnotation(Generate.class)).name();
        String className = clazz.getSimpleName();
        return getName(annotationName, className);
    }

    static public String getName(Element element) {
        checkNotNull(element);

        String annotationName = element.getAnnotation(Generate.class).name();
        String className = element.getSimpleName().toString();
        return getName(annotationName, className);
    }

    static private String getName(String annotationName, String className) {
        checkNotNull(annotationName);
        checkNotNull(className);

        if (!"".equals(annotationName)) {
            return StringUtils.uncapitalize(annotationName);
        } else {
            return StringUtils.uncapitalize(className);
        }
    }

    static private String getExtendedClasses(String annotationName, String className) {
        checkNotNull(annotationName);
        checkNotNull(className);

        if (!"".equals(annotationName)) {
            return StringUtils.uncapitalize(annotationName);
        } else {
            return StringUtils.uncapitalize(className);
        }
    }
}
