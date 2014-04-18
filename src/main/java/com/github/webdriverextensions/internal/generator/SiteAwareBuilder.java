package com.github.webdriverextensions.internal.generator;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JMod;
import java.io.IOException;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import com.github.webdriverextensions.WebRepository;
import com.github.webdriverextensions.internal.GeneratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class SiteAwareBuilder implements Builder<Boolean> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SiteAwareBuilder.class);
    // Input Elements
    private ProcessingEnvironment processingEnv;
    private TypeElement siteObjectElement;
    private Set<TypeElement> siteObjectElements;
    private Set<TypeElement> pageObjectElements;
    private JCodeModel codeModel;
    // JClasses
    private JDefinedClass siteAwareRepositoryClass;
    private Set<JClass> siteObjectClasses;
    private Set<JClass> pageObjectClasses;

    public SiteAwareBuilder(ProcessingEnvironment processingEnv,
            TypeElement siteObjectElement,
            Set<TypeElement> siteObjectElements,
            Set<TypeElement> pageObjectElements) {
        this.processingEnv = processingEnv;
        this.siteObjectElement = siteObjectElement;
        this.siteObjectElements = siteObjectElements;
        this.pageObjectElements = pageObjectElements;
    }

    @Override
    public Boolean build() {
        try {
            init();
            fields();
            constructors();
            methods();
            generate();
            return true;
        } catch (IOException ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return false;
        } catch (JClassAlreadyExistsException ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return false;
        }


    }

    private void init() throws JClassAlreadyExistsException {
        codeModel = new JCodeModel();
        siteAwareRepositoryClass = codeModel._class(JMod.PUBLIC | JMod.ABSTRACT, ElementUtils.getPackageName(siteObjectElement) + "." + StringUtils.capitalize(GeneratorUtils.getName(siteObjectElement)) + "AwareRepository", ClassType.CLASS);
        siteAwareRepositoryClass._extends(codeModel.ref(WebRepository.class));
        siteObjectClasses = getCodeModelRefs(siteObjectElements);
        pageObjectClasses = getCodeModelRefs(pageObjectElements);
    }

    private void fields() {
        // Declare SiteObjects
        for (JClass siteObjectClass : siteObjectClasses) {
            siteAwareRepositoryClass.field(JMod.PUBLIC, siteObjectClass, getSiteObjectFieldName(siteObjectClass));
        }

        // Declare PageObjects
        for (JClass pageObjectClass : pageObjectClasses) {
            siteAwareRepositoryClass.field(JMod.PUBLIC, pageObjectClass, getPageObjectFieldName(pageObjectClass));
        }
    }

    private void constructors() {
    }

    private void methods() {
    }

    private void generate() throws IOException {
        CodeWriter codeWriter = new ProcessingEnvironmentCodeWriter(processingEnv);
        codeModel.build(codeWriter);
    }

    private Set<JClass> getCodeModelRefs(Set<TypeElement> elements) {
        Set<JClass> codeModeModelRefs = new LinkedHashSet<JClass>();
        for (TypeElement element : elements) {
            codeModeModelRefs.add(codeModel.ref(element.getQualifiedName().toString()));
        }
        return codeModeModelRefs;
    }


    private String getSiteObjectFieldName() {
        return GeneratorUtils.getName(siteObjectElement);
    }

    private String getSiteObjectFieldName(JClass siteObjectClass) {
        for (TypeElement siteObjectElement : siteObjectElements) {
            if (siteObjectElement.getQualifiedName().toString().equals(siteObjectClass.fullName())) {
                return GeneratorUtils.getName(siteObjectElement);
            }
        }
        return null;
    }

    private String getPageObjectFieldName(JClass pageObjectClass) {
        for (TypeElement pageObjectElement : pageObjectElements) {
            if (pageObjectElement.getQualifiedName().toString().equals(pageObjectClass.fullName())) {
                return GeneratorUtils.getName(pageObjectElement);
            }
        }
        return null;
    }
}
