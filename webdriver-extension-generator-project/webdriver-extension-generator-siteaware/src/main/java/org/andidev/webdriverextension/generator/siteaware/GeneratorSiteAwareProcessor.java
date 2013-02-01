package org.andidev.webdriverextension.generator.siteaware;

import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import org.andidev.annotationprocessorvelocityutils.AbstractExtendedProcessor;
import org.andidev.annotationprocessorvelocityutils.ClassMetaData;
import org.andidev.annotationprocessorvelocityutils.ElementUtils;
import org.andidev.webdriverextension.annotation.PageObject;
import org.andidev.webdriverextension.annotation.SiteObject;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.annotation.SiteObject"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class GeneratorSiteAwareProcessor extends AbstractExtendedProcessor {

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
        VelocityContext metaData = createMetaData();
        generateClasses(metaData);

        debug("PROCESSING DONE");
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private void validateAnnotations() {
        debug("VALIDATING ANNOTATIONS");
        validateSiteAnnotation();
        debug("VALIDATED ANNOTATIONS");
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

    private ClassMetaData createSiteAwareMetaData() {
        // Create Default Meta Data
        ClassMetaData siteAwareMetaData = new ClassMetaData();

        // Validate Annotations
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(SiteObject.class);
        if (siteElements.isEmpty()) {
            return siteAwareMetaData;
        }

        // Override Meta Data with Class Data
        Element siteElement = (Element) siteElements.toArray()[0];
        debug("Creating SiteAware Meta Data from: " + siteElement);
        siteAwareMetaData.setPackageName(ElementUtils.getPackageName(siteElement));
        siteAwareMetaData.setClassName("SiteAware");
//        siteAwareMetaData.setClassName(StringUtils.removeEnd(StringUtils.removeEnd(ProcessorUtils.getClassName(siteElement), "Bot"), "Model") + "Aware");

        return siteAwareMetaData;
    }

    private VelocityContext createMetaData() {
        debug("CREATING METADATA");
        // Create Meta Data
        VelocityContext metaData = new VelocityContext();
        metaData.put("siteAware", createSiteAwareMetaData());
        metaData.put("site", createSiteMetaData());
        metaData.put("pages", createPagesMetaData());
        debug("CREATED METADATA");
        return metaData;
    }

    private ClassMetaData createSiteMetaData() {
        // Create Default Meta Data
        ClassMetaData siteMetaData = new ClassMetaData();

        // Validate Annotations
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(SiteObject.class);
        if (siteElements.isEmpty()) {
            return siteMetaData;
        }

        // Override Meta Data with Class Data
        Element siteElement = (Element) siteElements.toArray()[0];
        debug("Creating Site Meta Data from: " + siteElement);
        siteMetaData.setPackageName(ElementUtils.getPackageName(siteElement));
        siteMetaData.setClassName(ElementUtils.getClassName(siteElement));
        String name = siteElement.getAnnotation(SiteObject.class).name();
        if (!StringUtils.isEmpty(name)) {
            siteMetaData.setFieldName(name);
        } else {
            siteMetaData.setFieldName(StringUtils.uncapitalize(siteMetaData.getClassName()));
        }

//        if (StringUtils.endsWith(siteMetaData.getClassName(), "SiteBot")) {
//            siteMetaData.setFieldName(StringUtils.removeEnd(StringUtils.uncapitalize(siteMetaData.getClassName()), "Bot"));
//        } else if (StringUtils.endsWith(siteMetaData.getClassName(), "SiteModel")) {
//            siteMetaData.setFieldName(StringUtils.removeEnd(StringUtils.uncapitalize(siteMetaData.getClassName()), "Model"));
//        } else if (StringUtils.endsWith(siteMetaData.getClassName(), "SiteObject")) {
//            siteMetaData.setFieldName(StringUtils.removeEnd(StringUtils.uncapitalize(siteMetaData.getClassName()), "Object"));
//        } else {
//            siteMetaData.setFieldName(StringUtils.uncapitalize(siteMetaData.getClassName()));
//        }

        return siteMetaData;
    }

    private Set<ClassMetaData> createPagesMetaData() {
        Set<ClassMetaData> pagesMetaData = new TreeSet();
        Set<? extends Element> pageElements = roundEnvironment.getElementsAnnotatedWith(PageObject.class);
        debug("Creating Page Meta Data from: " + pageElements);
        for (Element pageElement : pageElements) {
            // Create Default Meta Data
            ClassMetaData pageMetaData = new ClassMetaData();
            pageMetaData.setPackageName(ElementUtils.getPackageName(pageElement));
            pageMetaData.setClassName(ElementUtils.getClassName(pageElement));
            String name = pageElement.getAnnotation(PageObject.class).name();
            if (!StringUtils.isEmpty(name)) {
                pageMetaData.setFieldName(name);
            } else {
                pageMetaData.setFieldName(StringUtils.uncapitalize(pageMetaData.getClassName()));
            }
//            if (StringUtils.endsWith(pageMetaData.getClassName(), "PageBot")) {
//                pageMetaData.setFieldName(StringUtils.removeEnd(StringUtils.uncapitalize(pageMetaData.getClassName()), "Bot"));
//            } else if (StringUtils.endsWith(pageMetaData.getClassName(), "PageModel")) {
//                pageMetaData.setFieldName(StringUtils.removeEnd(StringUtils.uncapitalize(pageMetaData.getClassName()), "Model"));
//            } else if (StringUtils.endsWith(pageMetaData.getClassName(), "PageObject")) {
//                pageMetaData.setFieldName(StringUtils.removeEnd(StringUtils.uncapitalize(pageMetaData.getClassName()), "Object"));
//            } else {
//                pageMetaData.setFieldName(StringUtils.uncapitalize(pageMetaData.getClassName()));
//            }
            pagesMetaData.add(pageMetaData);
        }

        return pagesMetaData;
    }

    private void generateClasses(VelocityContext metaData) {
        debug("GENERATING CLASSES");
        generateSiteAwareClass(metaData);
        debug("GENERATED CLASSES");
    }

    private void generateSiteAwareClass(VelocityContext metaData) {
        ClassMetaData siteAwareMetaData = (ClassMetaData) metaData.get("siteAware");
        String filePackage = siteAwareMetaData.getPackageName();
        String fileName = siteAwareMetaData.getClassName();
        generateClass("SiteAware.java.template", metaData, filePackage, fileName);
    }
}