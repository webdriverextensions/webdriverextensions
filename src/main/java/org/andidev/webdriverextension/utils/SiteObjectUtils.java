package org.andidev.webdriverextension.utils;

import static com.google.common.base.Preconditions.*;
import javax.lang.model.element.Element;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.annotation.SiteObject;
import org.apache.commons.lang3.StringUtils;

public class SiteObjectUtils {

    static public String getName(Class<WebPage> clazz) {
        checkNotNull(clazz);

        String annotationName = ((SiteObject) clazz.getAnnotation(SiteObject.class)).name();
        String className = clazz.getSimpleName();
        return getName(annotationName, className);
    }

    static public String getName(Element element) {
        checkNotNull(element);

        String annotationName = element.getAnnotation(SiteObject.class).name();
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
