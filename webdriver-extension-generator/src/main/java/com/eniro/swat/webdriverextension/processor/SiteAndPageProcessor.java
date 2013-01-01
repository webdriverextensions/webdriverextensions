package com.eniro.swat.webdriverextension.processor;

import com.eniro.swat.webdriverextension.annotation.Page;
import com.eniro.swat.webdriverextension.annotation.Site;
import com.eniro.swat.webdriverextension.processor.metadata.PageMetaData;
import com.eniro.swat.webdriverextension.processor.metadata.SiteBaseMetaData;
import com.eniro.swat.webdriverextension.processor.metadata.SiteMetaData;
import com.eniro.swat.webdriverextension.processor.metadata.SiteTestBaseMetaData;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

@SupportedAnnotationTypes({"com.eniro.swat.webdriverextension.annotation.Site"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class SiteAndPageProcessor extends AbstractExtendedProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        super.init(roundEnvironment);
        debug("PROCESSING: " + annotations);
        
        // Return if no annotations where found
        if (annotations.isEmpty()) {
            return true;
        }
        
        // Generate Class
        validateAnnotations();
        VelocityContext metaData = createMetaData();
        generateClasses(metaData);

        return true;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    private VelocityContext createMetaData() {
        // Create Meta Data
        VelocityContext metaData = new VelocityContext();
        metaData.put("siteTestBase", createSiteTestBaseMetaData());
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

    private SiteTestBaseMetaData createSiteTestBaseMetaData() {
        // Create Default Meta Data
        SiteTestBaseMetaData siteTestBaseMetaData = new SiteTestBaseMetaData();

        // Validate Annotations
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(Site.class);
        if (siteElements.isEmpty()) {
            return siteTestBaseMetaData;
        }

        // Override Meta Data with Class Data
        TypeElement siteElement = (TypeElement) siteElements.toArray()[0];
        debug("Creating SiteTestBase Meta Data from: " + siteElement);
        siteTestBaseMetaData.setPackageName(ProcessorUtils.getPackageName(siteElement));
        siteTestBaseMetaData.setClassName(ProcessorUtils.getClassName(siteElement) + "TestBase");

        return siteTestBaseMetaData;
    }

    private SiteMetaData createSiteMetaData() {
        // Create Default Meta Data
        SiteMetaData siteMetaData = new SiteMetaData();

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

    private SiteBaseMetaData createSiteBaseMetaData() {
        // Create Default Meta Data
        SiteBaseMetaData siteBaseMetaData = new SiteBaseMetaData();

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

    private Set<PageMetaData> createPagesMetaData() {
        Set<PageMetaData> pagesMetaData = new TreeSet();
        Set<? extends Element> pageElements = roundEnvironment.getElementsAnnotatedWith(Page.class);
        debug("Creating Page Meta Data from: " + pageElements);
        for (Element pageElement : pageElements) {
            // Create Default Meta Data
            PageMetaData pageMetaData = new PageMetaData();
            pageMetaData.setPackageName(ProcessorUtils.getPackageName((TypeElement) pageElement));
            pageMetaData.setClassName(ProcessorUtils.getClassName((TypeElement) pageElement));
            pageMetaData.setFieldName(StringUtils.uncapitalize(pageMetaData.getClassName()));

            pagesMetaData.add(pageMetaData);
        }

        return pagesMetaData;
    }

    private void generateClasses(VelocityContext metaData) {
        generateSiteTestClass(metaData);
        generateSiteClass(metaData);
    }

    private void generateSiteClass(VelocityContext metaData) {
        SiteBaseMetaData siteBaseMetaData = (SiteBaseMetaData) metaData.get("siteBase");
        String filePackage = siteBaseMetaData.getPackageName();
        String fileName = siteBaseMetaData.getClassName();
        generateClass("SiteBase.java.template", metaData, filePackage, fileName);
    }

    private void generateSiteTestClass(VelocityContext metaData) {
        SiteTestBaseMetaData siteTestMetaData = (SiteTestBaseMetaData) metaData.get("siteTestBase");
        String filePackage = siteTestMetaData.getPackageName();
        String fileName = siteTestMetaData.getClassName();
        generateClass("SiteTestBase.java.template", metaData, filePackage, fileName);
    }
}
