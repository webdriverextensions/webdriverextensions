package org.andidev.webdriverextension.utils;

import static com.google.common.base.Preconditions.*;
import javax.lang.model.element.Element;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.annotation.PageObject;
import org.apache.commons.lang3.StringUtils;

public class PageObjectUtils {

    static public String getName(Class<WebPage> clazz) {
        checkNotNull(clazz);

        String annotationName = ((PageObject) clazz.getAnnotation(PageObject.class)).name();
        String className = clazz.getSimpleName();
        return getName(annotationName, className);
    }

    static public String getName(Element element) {
        checkNotNull(element);

        String annotationName = element.getAnnotation(PageObject.class).name();
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
}
