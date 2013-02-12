package org.andidev.webdriverextension.generator.sitemodel;

import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import org.andidev.annotationprocessorutils.AbstractGeneratorProcessor;
import org.andidev.webdriverextension.annotation.PageObject;
import org.andidev.webdriverextension.annotation.SiteObject;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.annotation.SiteObject"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SiteModelGeneratorProcessor extends AbstractGeneratorProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        super.init(roundEnvironment);
        debug("PROCESSING: " + annotations);

        // Return if no annotations where found
        if (annotations.isEmpty()) {
            return false;
        }

        // Generate
        validateAnnotations();
        generateClasses();

        debug("PROCESSING DONE");
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public void validateAnnotations() {
        debug("VALIDATING ANNOTATIONS");
        validateSiteAnnotation();
        validatePageAnnotation();
        debug("VALIDATED ANNOTATIONS");
    }

    @Override
    public void generateClasses() {
        debug("GENERATING CLASSES");
        generateSiteClass();
        debug("GENERATED CLASSES");
    }

    private void validateSiteAnnotation() {
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(SiteObject.class);
        debug("@SiteObject Annotated Classes: " + siteElements);
        if (siteElements.isEmpty()) {
            warn("No @SiteObject annotation was found! Using default name SiteModel for generated site class.");
        }
        if (siteElements.size() > 1) {
            error("More than one @SiteObject annotation was found! Only one @SiteObject annotation must be set. Please ensure that and recompile!");
        }
    }

    private void validatePageAnnotation() {
        Set<? extends Element> pageElements = roundEnvironment.getElementsAnnotatedWith(PageObject.class);
        debug("@PageObject Annotated Classes: " + pageElements);
        if (pageElements.isEmpty()) {
            warn("No @PageObject annotations where found! Nothing to add to generated site class.");
        }
    }

    private void generateSiteClass() {
        SiteModelBuilder smb = new SiteModelBuilder(processingEnv,
                (TypeElement) (roundEnvironment.getElementsAnnotatedWith(SiteObject.class).iterator().next()),
                (Set<TypeElement>) roundEnvironment.getElementsAnnotatedWith(PageObject.class));
        smb.build();
    }
}