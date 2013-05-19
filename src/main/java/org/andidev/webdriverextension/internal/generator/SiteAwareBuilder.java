package org.andidev.webdriverextension.internal.generator;

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
import static org.andidev.annotationprocessorutils.ElementUtils.*;
import org.andidev.annotationprocessorutils.ProcessingEnvironmentCodeWriter;
import org.andidev.webdriverextension.internal.GeneratorUtils;
import org.andidev.webdriverextension.JUnitBot;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.Builder;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.WebDriver;

//@Slf4j
public class SiteAwareBuilder implements Builder<Boolean> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SiteAwareBuilder.class);
    // Input Elements
    private ProcessingEnvironment processingEnv;
    private TypeElement siteObjectElement;
    private Set<TypeElement> siteObjectElements;
    private Set<TypeElement> pageObjectElements;
    private TypeElement extendedObjectElement;
    private JCodeModel codeModel;
    // JClasses
    private JDefinedClass siteAwareClass;
    private JClass extendedObjectClass;
    private JClass webDriverClass;
    private JClass jUnitBotClass;
    private JClass siteObjectClass;
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

    public SiteAwareBuilder(ProcessingEnvironment processingEnv,
            TypeElement siteObjectElement,
            Set<TypeElement> siteObjectElements,
            Set<TypeElement> pageObjectElements,
            TypeElement extendedObjectElement) {
        this.processingEnv = processingEnv;
        this.siteObjectElement = siteObjectElement;
        this.siteObjectElements = siteObjectElements;
        this.pageObjectElements = pageObjectElements;
        this.extendedObjectElement = extendedObjectElement;
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
        if (isExtended()) {
            siteAwareClass = codeModel._class(JMod.PUBLIC | JMod.ABSTRACT, getPackageName(siteObjectElement) + "." + StringUtils.capitalize(GeneratorUtils.getName(siteObjectElement)) + "Aware" + getClassName(extendedObjectElement), ClassType.CLASS);
            extendedObjectClass = codeModel.ref(getFullClassName(extendedObjectElement));
            siteAwareClass._extends(extendedObjectClass);
        } else {
            siteAwareClass = codeModel._class(JMod.PUBLIC | JMod.ABSTRACT, getPackageName(siteObjectElement) + "." + StringUtils.capitalize(GeneratorUtils.getName(siteObjectElement)) + "Aware", ClassType.CLASS);
        }
        webDriverClass = codeModel.ref(WebDriver.class);
        jUnitBotClass = codeModel.ref(JUnitBot.class);
        siteObjectClass = codeModel.ref(siteObjectElement.getQualifiedName().toString());
        siteObjectClasses = getCodeModelRefs(siteObjectElements);
        pageObjectClasses = getCodeModelRefs(pageObjectElements);
    }

    private void fields() {
        // Declare SiteObjects
        for (JClass siteObjectClass : siteObjectClasses) {
            siteAwareClass.field(JMod.PUBLIC, siteObjectClass, getSiteObjectFieldName(siteObjectClass));
        }

        // Declare PageObjects
        for (JClass pageObjectClass : pageObjectClasses) {
            siteAwareClass.field(JMod.PUBLIC, pageObjectClass, getPageObjectFieldName(pageObjectClass));
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

    private boolean isExtended() {
        return extendedObjectElement != null;
    }
}
