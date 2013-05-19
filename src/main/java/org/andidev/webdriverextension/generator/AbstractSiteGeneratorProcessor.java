package org.andidev.webdriverextension.generator;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import org.andidev.webdriverextension.internal.generator.AbstractGeneratorProcessor;
import org.andidev.webdriverextension.internal.generator.AbstractSiteBuilder;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.generator.annotations.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class AbstractSiteGeneratorProcessor extends AbstractGeneratorProcessor {

    @Override
    public void generateClasses() {
        for (TypeElement siteClass : getReferencedSiteClasses()) {
            generateAbstractSiteClass(siteClass);
        }
    }

    private void generateAbstractSiteClass(TypeElement siteClass) {
        debug("Generating AbstractSite for class: " + siteClass.getSimpleName() + " with Page Classes: " + getAnnotatedPageClasses(siteClass));
        AbstractSiteBuilder smb = new AbstractSiteBuilder(processingEnv,
                siteClass,
                getAnnotatedPageClasses(siteClass));
        smb.build();
    }
}