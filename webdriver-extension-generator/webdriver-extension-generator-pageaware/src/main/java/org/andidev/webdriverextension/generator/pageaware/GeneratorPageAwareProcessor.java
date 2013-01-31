package org.andidev.webdriverextension.generator.pageaware;

import java.util.Set;
import java.util.TreeSet;
import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import org.andidev.webdriverextension.annotation.PageObject;
import org.andidev.webdriverextension.annotation.SiteObject;
import org.andidev.webdriverextension.generator.util.AbstractExtendedProcessor;
import org.andidev.webdriverextension.generator.util.ClassMetaData;
import org.andidev.webdriverextension.generator.util.ElementUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;

@SupportedAnnotationTypes({"org.andidev.webdriverextension.annotation.SiteObject"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class GeneratorPageAwareProcessor extends AbstractExtendedProcessor {

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

    private VelocityContext createMetaData() {
        debug("CREATING METADATA");
        // Create Meta Data
        VelocityContext metaData = new VelocityContext();
        metaData.put("pageAware", createPageAwareMetaData());
        metaData.put("pages", createPagesMetaData());
        debug("CREATED METADATA");
        return metaData;
    }

    private void validateAnnotations() {
        debug("VALIDATING ANNOTATIONS");
        validateSiteAnnotation();
        validatePageAnnotation();
        debug("VALIDATED ANNOTATIONS");
    }

    private void validateSiteAnnotation() {
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(SiteObject.class);
        debug("@SiteObject Annotated Classes: " + siteElements);
        if (siteElements.isEmpty()) {
            warn("No @SiteObject annotation was found! Using default name PageAware for generated site class.");
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

    private ClassMetaData createPageAwareMetaData() {
        // Create Default Meta Data
        ClassMetaData pageAwareMetaData = new ClassMetaData();

        // Validate Annotations
        Set<? extends Element> siteElements = roundEnvironment.getElementsAnnotatedWith(SiteObject.class);
        if (siteElements.isEmpty()) {
            return pageAwareMetaData;
        }

        // Override Meta Data with Class Data
        Element siteElement = (Element) siteElements.toArray()[0];
        debug("Creating PageAware Meta Data from: " + siteElement);
        pageAwareMetaData.setPackageName(ElementUtils.getPackageName(siteElement));
        pageAwareMetaData.setClassName("PageAware");
//        pageAwareMetaData.setClassName(StringUtils.removeEnd(StringUtils.removeEnd(StringUtils.removeEnd(ProcessorUtils.getClassName(siteElement), "Bot"), "Model"), "Site") + "PageAware");

        return pageAwareMetaData;
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
        generateSiteClass(metaData);
        debug("GENERATED CLASSES");
    }

    private void generateSiteClass(VelocityContext metaData) {
        ClassMetaData pageAwareMetaData = (ClassMetaData) metaData.get("pageAware");
        String filePackage = pageAwareMetaData.getPackageName();
        String fileName = pageAwareMetaData.getClassName();
        generateClass("PageAware.java.template", metaData, filePackage, fileName);
    }
}