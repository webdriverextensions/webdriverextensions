package org.andidev.webdriverextension.generator;

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
import org.andidev.webdriverextension.annotations.SiteAwareExtends;
import org.andidev.webdriverextension.internal.generator.AbstractGeneratorProcessor;
import org.andidev.webdriverextension.internal.generator.SiteAwareBuilder;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.generator.annotations.Generate"})
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
                getAnnotatedSiteClasses(),
                getAnnotatedPageClasses(siteClass));
        builder.build();
    }

    private void generateSiteAwareClassExtendedWith(TypeElement siteClass, TypeElement exdendsClass) {
        debug("Generating SiteAware class with Site Classes: " + siteClass.getSimpleName() + " and Page Classes: " + getAnnotatedPageClasses(siteClass));
        SiteAwareBuilder builder = new SiteAwareBuilder(processingEnv,
                siteClass,
                getAnnotatedSiteClasses(),
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