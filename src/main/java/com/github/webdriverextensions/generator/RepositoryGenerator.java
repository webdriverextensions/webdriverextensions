package com.github.webdriverextensions.generator;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.github.webdriverextensions.internal.generator.GeneratorProcessor;
import com.github.webdriverextensions.internal.generator.RepositoryBuilder;

@SupportedAnnotationTypes({"com.github.webdriverextensions.generator.annotations.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class RepositoryGenerator extends GeneratorProcessor {

    @Override
    public void generateClass() {
        TypeElement siteClass = getAnnotatedSiteClasses().iterator().next();
        debug("Creating GeneratedRepository class with Site Classes: " + siteClass.getSimpleName() + " and Page Classes: " + getAnnotatedPageClasses());
        RepositoryBuilder builder = new RepositoryBuilder(processingEnv,
                siteClass,
                getAnnotatedPageClasses());
        builder.build();
    }
}
