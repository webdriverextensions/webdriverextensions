package com.github.webdriverextensions.generator;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import com.github.webdriverextensions.internal.generator.GeneratorProcessor;
import com.github.webdriverextensions.internal.generator.WebSiteBuilder;

@SupportedAnnotationTypes({"com.github.webdriverextensions.generator.annotations.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class WebSiteGenerator extends GeneratorProcessor {

    @Override
    public void generateClass() {
        debug("Creating GeneratedWebSite with Page Classes: " + getAnnotatedPageClasses());
        WebSiteBuilder smb = new WebSiteBuilder(processingEnv,
                getAnnotatedPageClasses());
        smb.build();
    }
}
