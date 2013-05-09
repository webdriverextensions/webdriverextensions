package org.andidev.webdriverextension.generator;

import java.util.Set;
import java.util.LinkedHashSet;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import org.andidev.annotationprocessorutils.AbstractExtendedProcessor;
import org.andidev.webdriverextension.WebPage;
import org.andidev.webdriverextension.annotation.Generate;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.annotation.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public abstract class AbstractGeneratorProcessor extends AbstractExtendedProcessor {

    private Set<TypeElement> annotatedClasses;
    private Set<TypeElement> annotatedSiteClasses;
    private Set<TypeElement> annotatedPageClasses;
    private boolean generated = false;

    public Set<TypeElement> getAnnotatedClasses() {
        if (annotatedClasses == null) {
            loadAnnotatedClasses();
        }
        return annotatedClasses;
    }

    public Set<TypeElement> getReferencedSiteClasses() {
        Set<TypeElement> siteClasses = new LinkedHashSet<TypeElement>();
        siteClasses.addAll(getAnnotatedSiteClasses());
        siteClasses.addAll(getSiteClassesFromPageGenerics());
        return siteClasses;
    }

    public Set<TypeElement> getAnnotatedSiteClasses() {
        if (annotatedSiteClasses == null) {
            loadAnnotatedSiteClasses();
        }
        return annotatedSiteClasses;
    }

    public Set<TypeElement> getAnnotatedPageClasses() {
        if (annotatedPageClasses == null) {
            loadAnnotatedPageClasses();
        }
        return annotatedPageClasses;
    }

    public Set<TypeElement> getAnnotatedPageClasses(TypeElement siteClass) {
        Set<TypeElement> pageClasses = new LinkedHashSet<TypeElement>();
        for (TypeElement element : getAnnotatedPageClasses()) {
            if (isPageClass(element) && hasGenericSiteClass(element, siteClass)) {
                pageClasses.add(element);
            }
        }
        return pageClasses;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (!generated) {
            super.init(roundEnvironment);
//            for (Element element : roundEnvironment.getRootElements()) {
//                System.out.println("Processing element " + ReflectionToStringBuilder.reflectionToString(element, ToStringStyle.MULTI_LINE_STYLE));
//            }
            debug("Processing @" + Generate.class.getSimpleName() + " Annotations");

            if (validate()) {
                generateClasses();
            };

            debug("Processed " + Generate.class.getSimpleName() + " Annotations");
            generated = true;
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    public boolean validate() {
        debug("Validating data used by the generator");
        if (validateHasAnnotations()
                && validateAnnotatedSiteClasses()
                && validateAnnotatedPageClasses()) {
            debug("Configuration is valid! Continuing with the generation!");
            return true;
        }
        error("Configuration is invalid! Please fix configuration and recompile!");
        return false;
    }

    public void generateClasses() {
    }

    private void loadAnnotatedClasses() {
        annotatedClasses = (Set<TypeElement>) getClassElementsAnnotatedWith(Generate.class);
    }

    private void loadAnnotatedSiteClasses() {
        annotatedSiteClasses = new LinkedHashSet<TypeElement>();
        for (Element annotatedClass : getAnnotatedClasses()) {
            if (isSiteClass(annotatedClass)) {
                annotatedSiteClasses.add((TypeElement) annotatedClass);
            }
        }
    }

    private void loadAnnotatedPageClasses() {
        annotatedPageClasses = new LinkedHashSet<TypeElement>();
        for (Element annotatedClass : getAnnotatedClasses()) {
            if (isPageClass(annotatedClass)) {
                annotatedPageClasses.add((TypeElement) annotatedClass);
            }
        }
    }

    private boolean validateHasAnnotations() {
        // Return if no annotations where found
        if (getAnnotatedPageClasses().isEmpty() && getAnnotatedSiteClasses().isEmpty()) {
            warn("No Classes annotated with @" + Generate.class.getSimpleName() + " annotation where found. Nothing to generate!");
            return false;
        } else {
            return true;
        }

    }

    private boolean validateAnnotatedSiteClasses() {
        if (getAnnotatedSiteClasses().isEmpty()) {
            warn("No Sites annotated with @" + Generate.class.getSimpleName() + " annotation was found!");
        } else {
            debug("Sites annotated with @" + Generate.class.getSimpleName() + " annotation: " + getAnnotatedSiteClasses());
        }
        return true;
    }

    private boolean validateAnnotatedPageClasses() {
        if (getAnnotatedPageClasses().isEmpty()) {
            warn("No Pages annotated with @" + Generate.class.getSimpleName() + " annotation where found!");
        } else {
            debug("Pages annotated with @" + Generate.class.getSimpleName() + " annotation: " + getAnnotatedPageClasses());
        }
        return true;
    }

    private Set<TypeElement> getSiteClassesFromPageGenerics() {
        return new LinkedHashSet<TypeElement>(); // TODO: Implement!!!
    }

    private boolean isSiteClass(Element element) {
        if (!isPageClass(element)) {
            // If element is not page class assume it is a site class
            return true;
        } else {
            return false;
        }
    }

    private boolean isPageClass(Element element) {
        TypeMirror type = element.asType();
        TypeMirror webPageWildcardType = typeUtils.getDeclaredType(
                elementUtils.getTypeElement(WebPage.class.getCanonicalName()),
                typeUtils.getWildcardType(null, null));
        if (typeUtils.isAssignable(type, webPageWildcardType)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasGenericSiteClass(TypeElement pageClass, TypeElement siteClass) {
        TypeMirror webPageType = pageClass.asType();
        TypeMirror webPageSiteClass = typeUtils.getDeclaredType(
                elementUtils.getTypeElement(WebPage.class.getCanonicalName()),
                siteClass.asType());
        if (typeUtils.isAssignable(webPageType, webPageSiteClass)) {
            return true;
        } else {
            return false;
        }
    }

    private TypeElement getGenericSiteClass(TypeElement pageClass) {
        System.out.println(ReflectionToStringBuilder.reflectionToString(pageClass, ToStringStyle.MULTI_LINE_STYLE));
        return (TypeElement) pageClass.getTypeParameters().iterator().next();
    }
}