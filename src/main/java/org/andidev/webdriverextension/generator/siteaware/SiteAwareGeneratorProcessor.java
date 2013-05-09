package org.andidev.webdriverextension.generator.siteaware;

import com.sun.tools.javac.code.Attribute;
import com.sun.tools.javac.code.Type;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.TypeElement;
import org.andidev.annotationprocessorutils.AnnotationUtils;
import org.andidev.webdriverextension.annotation.SiteAwareExtends;
import org.andidev.webdriverextension.generator.AbstractGeneratorProcessor;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.annotation.Generate"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SiteAwareGeneratorProcessor extends AbstractGeneratorProcessor {

    @Override
    public void generateClasses() {
        for (TypeElement siteClass : getAnnotatedSiteClasses()) {
            generateSiteAwareClass(siteClass);
            for (TypeElement exdendsClass : getSiteAwareExtendsClasses(siteClass)) {
                generateSiteAwareClassExtendedWith(siteClass, exdendsClass);
            }
        }
    }

    private void generateSiteAwareClass(TypeElement siteClass) {
        debug("Generating SiteAware class with Site Classes: " + siteClass.getSimpleName() + " and Page Classes: " + getAnnotatedPageClasses(siteClass));
        SiteAwareBuilder builder = new SiteAwareBuilder(processingEnv,
                siteClass,
                getAnnotatedPageClasses(siteClass));
        builder.build();
    }

    private void generateSiteAwareClassExtendedWith(TypeElement siteClass, TypeElement exdendsClass) {
        debug("Generating SiteAware class with Site Classes: " + siteClass.getSimpleName() + " and Page Classes: " + getAnnotatedPageClasses(siteClass));
        SiteAwareBuilder builder = new SiteAwareBuilder(processingEnv,
                siteClass,
                getAnnotatedPageClasses(siteClass),
                exdendsClass);
        builder.build();
    }

    private Set<TypeElement> getSiteAwareExtendsClasses(TypeElement siteClass) {
        AnnotationValue annotationValue = AnnotationUtils.getAnnotationValue("value", SiteAwareExtends.class, siteClass);
        if (annotationValue == null) {
            return new LinkedHashSet<TypeElement>();
        }
        Set<TypeElement> siteAwareExtendsClasses = new LinkedHashSet<TypeElement>();
        for (Attribute attribute : (List<Attribute>) annotationValue.getValue()) {
            Type type = (Type) attribute.getValue();
            siteAwareExtendsClasses.add((TypeElement) typeUtils.asElement(type));
        }
        return siteAwareExtendsClasses;
    }
}