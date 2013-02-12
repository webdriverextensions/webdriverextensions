package org.andidev.webdriverextension.generator.siteaware;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.Types;
import org.andidev.annotationprocessorutils.AbstractGeneratorProcessor;
import org.andidev.annotationprocessorutils.AnnotationUtils;
import org.andidev.webdriverextension.annotation.PageObject;
import org.andidev.webdriverextension.annotation.SiteAwareExtends;
import org.andidev.webdriverextension.annotation.SiteObject;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.annotation.SiteObject"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SiteAwareGeneratorProcessor extends AbstractGeneratorProcessor {

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
        debug("VALIDATED ANNOTATIONS");
    }

    @Override
    public void generateClasses() {
        debug("GENERATING CLASSES");
        generateSiteAwareClass();
        generateSiteAwareExtendsClasses();
        debug("GENERATED CLASSES");
    }

    private void validateSiteAnnotation() {
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(SiteObject.class);
        debug("@SiteObject Annotated Classes: " + siteElements);
        if (siteElements.isEmpty()) {
            warn("No @SiteObject annotation was found! Using default name Site for generated site class.");
        }
        if (siteElements.size() > 1) {
            error("More than one @SiteObject annotation was found! Only one @SiteObject annotation must be set. Please ensure that and recompile!");
        }
    }

    private void generateSiteAwareClass() {
        SiteAwareBuilder sab = new SiteAwareBuilder(processingEnv,
                (TypeElement) (roundEnvironment.getElementsAnnotatedWith(SiteObject.class).iterator().next()),
                (Set<TypeElement>) roundEnvironment.getElementsAnnotatedWith(PageObject.class));
        sab.build();
    }

    private void generateSiteAwareExtendsClasses() {
        for (TypeElement siteAwareExtendedClass : getSiteAwareExtendsClasses()) {
            generateSiteAwareExtendsClass(siteAwareExtendedClass);
        }
    }

    private void generateSiteAwareExtendsClass(TypeElement siteAwareExtendedClass) {
        SiteAwareBuilder sab = new SiteAwareBuilder(processingEnv,
                (TypeElement) (roundEnvironment.getElementsAnnotatedWith(SiteObject.class).iterator().next()),
                (Set<TypeElement>) roundEnvironment.getElementsAnnotatedWith(PageObject.class),
                (TypeElement) siteAwareExtendedClass);
        sab.build();
    }

    private boolean hasSiteAwareExtendsAnnotation() {
        return roundEnvironment.getElementsAnnotatedWith(SiteAwareExtends.class).iterator().hasNext();
    }

    private List<TypeElement> getSiteAwareExtendsClasses() {
        Types TypeUtils = this.processingEnv.getTypeUtils();

        AnnotationValue siteAwareExtends = null;

        List<TypeElement> values = new ArrayList<TypeElement>();

        Set<? extends Element> classElements = getClassElementsAnnotatedWith(SiteAwareExtends.class);
        for (Element classElement : classElements) {
            AnnotationValue annotationValue = AnnotationUtils.getAnnotationValue("value", SiteAwareExtends.class, classElement);
            for (Attribute attribute : (List<Attribute>) annotationValue.getValue()) {
                Type type = (Type) attribute.getValue();
                values.add((TypeElement) TypeUtils.asElement(type));
            }
        }
        return values;
    }

}