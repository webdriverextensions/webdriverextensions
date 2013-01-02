package org.andidev.webdriverextension.generator.sitebase;

import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import org.andidev.webdriverextension.annotation.Page;
import org.andidev.webdriverextension.annotation.Site;
import org.andidev.webdriverextension.generator.util.AbstractExtendedProcessor;
import org.andidev.webdriverextension.generator.util.ClassMetaData;
import org.andidev.webdriverextension.generator.util.ProcessorUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.annotation.Site"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class GeneratorSiteBaseProcessor extends AbstractExtendedProcessor {

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

        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private VelocityContext createMetaData() {
        // Create Meta Data
        VelocityContext metaData = new VelocityContext();
        metaData.put("site", createSiteMetaData());
        metaData.put("siteBase", createSiteBaseMetaData());
        metaData.put("pages", createPagesMetaData());
        return metaData;
    }

    private void validateAnnotations() {
        validateSiteAnnotation();
        validatePageAnnotation();
    }

    private void validateSiteAnnotation() {
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(Site.class);
        debug("@Site Annotated Classes: " + siteElements);
        if (siteElements.isEmpty()) {
            warn("No @Site annotation was found! Using default name SiteBase for generated site class.");
        }
        if (siteElements.size() > 1) {
            error("More than one @Site annotation was found! Only one @Site annotation must be set. Please ensure that and recompile!");
        }
    }

    private void validatePageAnnotation() {
        Set<? extends Element> pageElements = roundEnvironment.getElementsAnnotatedWith(Page.class);
        debug("@Page Annotated Classes: " + pageElements);
        if (pageElements.isEmpty()) {
            warn("No @Page annotations where found! Nothing to add to generated site class.");
        }
    }

    private ClassMetaData createSiteMetaData() {
        // Create Default Meta Data
        ClassMetaData siteMetaData = new ClassMetaData();

        // Validate Annotations
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(Site.class);
        if (siteElements.isEmpty()) {
            return siteMetaData;
        }

        // Override Meta Data with Class Data
        TypeElement siteElement = (TypeElement) siteElements.toArray()[0];
        debug("Creating Site Meta Data from: " + siteElement);
        siteMetaData.setPackageName(ProcessorUtils.getPackageName(siteElement));
        siteMetaData.setClassName(ProcessorUtils.getClassName(siteElement));
        siteMetaData.setFieldName(StringUtils.uncapitalize(siteMetaData.getClassName()));

        return siteMetaData;
    }

    private ClassMetaData createSiteBaseMetaData() {
        // Create Default Meta Data
        ClassMetaData siteBaseMetaData = new ClassMetaData();

        // Validate Annotations
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(Site.class);
        if (siteElements.isEmpty()) {
            return siteBaseMetaData;
        }

        // Override Meta Data with Class Data
        TypeElement siteElement = (TypeElement) siteElements.toArray()[0];
        debug("Creating SiteBase Meta Data from: " + siteElement);
        siteBaseMetaData.setPackageName(ProcessorUtils.getPackageName(siteElement));
        siteBaseMetaData.setClassName(ProcessorUtils.getClassName(siteElement) + "Base");
        siteBaseMetaData.setFieldName(StringUtils.uncapitalize(siteBaseMetaData.getClassName()));

        return siteBaseMetaData;
    }

    private Set<ClassMetaData> createPagesMetaData() {
        Set<ClassMetaData> pagesMetaData = new TreeSet();
        Set<? extends Element> pageElements = roundEnvironment.getElementsAnnotatedWith(Page.class);
        debug("Creating Page Meta Data from: " + pageElements);
        for (Element pageElement : pageElements) {
            // Create Default Meta Data
            ClassMetaData pageMetaData = new ClassMetaData();
            pageMetaData.setPackageName(ProcessorUtils.getPackageName((TypeElement) pageElement));
            pageMetaData.setClassName(ProcessorUtils.getClassName((TypeElement) pageElement));
            pageMetaData.setFieldName(StringUtils.uncapitalize(pageMetaData.getClassName()));

            pagesMetaData.add(pageMetaData);
        }

        return pagesMetaData;
    }

    private void generateClasses(VelocityContext metaData) {
        generateSiteClass(metaData);
    }

    private void generateSiteClass(VelocityContext metaData) {
        ClassMetaData siteBaseMetaData = (ClassMetaData) metaData.get("siteBase");
        String filePackage = siteBaseMetaData.getPackageName();
        String fileName = siteBaseMetaData.getClassName();
        generateClass("SiteBase.java.template", metaData, filePackage, fileName);
    }
}
