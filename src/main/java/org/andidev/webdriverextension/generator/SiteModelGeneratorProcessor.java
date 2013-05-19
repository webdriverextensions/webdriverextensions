package org.andidev.webdriverextension.generator;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import org.andidev.webdriverextension.internal.generator.AbstractGeneratorProcessor;
import org.andidev.webdriverextension.internal.generator.SiteModelBuilder;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.generator.annotations.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SiteModelGeneratorProcessor extends AbstractGeneratorProcessor {

    @Override
    public void generateClasses() {
        for (TypeElement siteClass : getReferencedSiteClasses()) {
            generateSiteModelClass(siteClass);
        }
    }

    private void generateSiteModelClass(TypeElement siteClass) {
        debug("Generating SiteModel for class: " + siteClass.getSimpleName() + " with Page Classes: " + getAnnotatedPageClasses(siteClass));
        SiteModelBuilder smb = new SiteModelBuilder(processingEnv,
                siteClass,
                getAnnotatedPageClasses(siteClass));
        smb.build();
    }
}