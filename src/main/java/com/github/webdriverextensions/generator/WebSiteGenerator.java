package com.github.webdriverextensions.generator;

import java.util.Set;
import java.util.LinkedHashSet;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import com.github.webdriverextensions.generator.annotations.AddToWebSite;
import com.github.webdriverextensions.internal.generator.WebSiteBuilder;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SupportedAnnotationTypes({"com.github.webdriverextensions.generator.annotations.AddToWebSite"})
@SupportedSourceVersion(SourceVersion.RELEASE_6)
public class WebSiteGenerator extends AbstractProcessor {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());

    protected boolean debug = true;
    final private Set<TypeElement> annotatedPageClasses = new LinkedHashSet<>();
    final private Set<TypeElement> annotatedRepositoryClasses = new LinkedHashSet<>();
    final private Set<TypeElement> annotatedOtherClasses = new LinkedHashSet<>();

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    protected void initAnnotations(RoundEnvironment roundEnvironment) {

        for (TypeElement typeElement : getAnnotatedClasses(roundEnvironment)) {
            if (isSiteClass(typeElement, processingEnv)) {
                warn("Cannot add WebSite " + typeElement.getQualifiedName() + " to GeneratedWebSite. Ignoring @" + AddToWebSite.class.getSimpleName() + " annotation.", processingEnv);
            } else if (isPageClass(typeElement, processingEnv)) {
                annotatedPageClasses.add(typeElement);
            } else if (isRepositoryClass(typeElement, processingEnv)) {
                annotatedRepositoryClasses.add(typeElement);
            } else {
                annotatedOtherClasses.add(typeElement);
            }
        }
    }

    public Set<TypeElement> getAnnotatedClasses(RoundEnvironment roundEnvironment) {
        return (Set<TypeElement>) roundEnvironment.getElementsAnnotatedWith(AddToWebSite.class);
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations.isEmpty()) {
            return true; // Skip processing if annotation is already processed
        }

        info("Processing @" + AddToWebSite.class.getSimpleName() + " annotations", processingEnv);
        initAnnotations(roundEnvironment);
        generateClass();
        return true;
    }

    public void generateClass() {
        info("Creating GeneratedWebSite class with " + "\n\tWebPages: " + annotatedPageClasses
                + "\n\tWebRepositories: " + annotatedRepositoryClasses + "\n\tOther: " + annotatedOtherClasses, processingEnv);
        WebSiteBuilder builder = new WebSiteBuilder(processingEnv,
                annotatedPageClasses, annotatedRepositoryClasses, annotatedOtherClasses);
        builder.build();
    }

}
