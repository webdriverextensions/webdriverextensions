package com.github.webdriverextensions.generator;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.github.webdriverextensions.internal.generator.AbstractGeneratorProcessor;
import com.github.webdriverextensions.internal.generator.SiteAwareBuilder;

@SupportedAnnotationTypes({"com.github.webdriverextensions.generator.annotations.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SiteAwareGeneratorProcessor extends AbstractGeneratorProcessor {

    @Override
    public void generateClass() {
        TypeElement siteClass = getAnnotatedSiteClasses().iterator().next();
        debug("Generating SiteAware class with Site Classes: " + siteClass.getSimpleName() + " and Page Classes: " + getAnnotatedPageClasses());
        SiteAwareBuilder builder = new SiteAwareBuilder(processingEnv,
                siteClass,
                getAnnotatedPageClasses());
        builder.build();
    }
}
