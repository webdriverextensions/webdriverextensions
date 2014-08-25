package com.github.webdriverextensions.generator;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.github.webdriverextensions.internal.generator.GeneratorProcessor;
import com.github.webdriverextensions.internal.generator.WebSiteBuilder;

@SupportedAnnotationTypes({"com.github.webdriverextensions.generator.annotations.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class WebSiteGenerator extends GeneratorProcessor {

    @Override
    public void generateClass() {
        TypeElement siteClass = getAnnotatedSiteClasses().iterator().next();
        debug("Creating GeneratedWebSite for class: " + siteClass.getSimpleName() + " with Page Classes: " + getAnnotatedPageClasses());
        WebSiteBuilder smb = new WebSiteBuilder(processingEnv,
                siteClass,
                getAnnotatedPageClasses());
        smb.build();
    }
}
