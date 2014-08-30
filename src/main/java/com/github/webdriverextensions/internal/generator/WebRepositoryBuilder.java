package com.github.webdriverextensions.internal.generator;

import com.github.webdriverextensions.WebRepository;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.annotateFieldWithFindByAnnotation;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.annotateFieldWithFindBysAnnotation;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.error;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.getFieldName;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.getFindByAnnotation;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.getFindBysAnnotation;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.hasFindByAnnotation;
import static com.github.webdriverextensions.internal.generator.GeneratorUtils.hasFindBysAnnotation;
import com.sun.codemodel.ClassType;
import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;
import com.sun.codemodel.JMod;
import java.io.IOException;
import java.util.Set;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class WebRepositoryBuilder implements Builder<Boolean> {

    // Input Elements
    final private ProcessingEnvironment processingEnv;
    final private Set<TypeElement> webSiteElements;
    final private Set<TypeElement> webPageElements;
    final private Set<TypeElement> webRepositoryElements;
    final private Set<TypeElement> otherElements;
    final private JCodeModel codeModel;
    // JClasses
    private JDefinedClass generatedWebRepositoryClass;

    public WebRepositoryBuilder(ProcessingEnvironment processingEnv, Set<TypeElement> webSiteElements,
            Set<TypeElement> webPageElements, Set<TypeElement> webRepositoryElements, Set<TypeElement> otherElements) {
        this.processingEnv = processingEnv;
        this.webSiteElements = webSiteElements;
        this.webPageElements = webPageElements;
        this.webRepositoryElements = webRepositoryElements;
        this.otherElements = otherElements;
        this.codeModel = new JCodeModel();
    }

    @Override
    public Boolean build() {
        try {
            createClass();
            createFields();
            generate();
            return true;
        } catch (IOException ex) {
            error("Failed to generate GeneratedWebRepository!", processingEnv);
            error(ExceptionUtils.getStackTrace(ex), processingEnv);
            return false;
        } catch (JClassAlreadyExistsException ex) {
            error("Failed to generate GeneratedWebRepository!", processingEnv);
            error(ExceptionUtils.getStackTrace(ex), processingEnv);
            return false;
        }

    }

    private void createClass() throws JClassAlreadyExistsException {
        generatedWebRepositoryClass = codeModel._class(JMod.PUBLIC | JMod.ABSTRACT, "com.github.webdriverextensions.generator.GeneratedWebRepository", ClassType.CLASS);
        generatedWebRepositoryClass._extends(codeModel.ref(WebRepository.class));
    }

    private void createFields() {
        // Declare WebSites
        for (TypeElement webSiteElement : webSiteElements) {
            JClass webSiteClass = codeModel.ref(webSiteElement.getQualifiedName().toString());
            generatedWebRepositoryClass.field(JMod.PUBLIC, webSiteClass, getFieldName(webSiteElement));
        }

        // Declare WebPages
        for (TypeElement webPageElement : webPageElements) {
            JClass webPageClass = codeModel.ref(webPageElement.getQualifiedName().toString());
            generatedWebRepositoryClass.field(JMod.PUBLIC, webPageClass, getFieldName(webPageElement));
        }

        // Declare WebRepositories
        for (TypeElement webRepositoryElement : webRepositoryElements) {
            JClass webRepositoryClass = codeModel.ref(webRepositoryElement.getQualifiedName().toString());
            generatedWebRepositoryClass.field(JMod.PUBLIC, webRepositoryClass, getFieldName(webRepositoryElement));
        }

        // Declare Other
        for (TypeElement otherElement : otherElements) {
            JClass otherClass = codeModel.ref(otherElement.getQualifiedName().toString());
            JFieldVar field = generatedWebRepositoryClass.field(JMod.PUBLIC, otherClass, getFieldName(otherElement));
            if (hasFindBysAnnotation(otherElement)) {
                annotateFieldWithFindBysAnnotation(field, getFindBysAnnotation(otherElement));
            }
            if (hasFindByAnnotation(otherElement)) {
                annotateFieldWithFindByAnnotation(field, getFindByAnnotation(otherElement));
            }
        }
    }

    private void generate() throws IOException {
        CodeWriter codeWriter = new ProcessingEnvironmentCodeWriter(processingEnv);
        codeModel.build(codeWriter);
    }
}
