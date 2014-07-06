package com.github.webdriverextensions.internal;

import static com.google.common.base.Preconditions.*;
import javax.lang.model.element.Element;
import com.github.webdriverextensions.WebPage;
import org.apache.commons.lang3.StringUtils;

public class GeneratorUtils {

    static public String getName(Class<WebPage> clazz) {
        checkNotNull(clazz);

        String className = clazz.getSimpleName();
        return StringUtils.uncapitalize(className);
    }

    static public String getName(Element element) {
        checkNotNull(element);

        String className = element.getSimpleName().toString();
        return StringUtils.uncapitalize(className);
    }
}
