package org.andidev.webdriverextension.generator.siteaware;

import com.sun.codemodel.ClassType;
import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JClass;
import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JVar;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.LinkedHashSet;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import static org.andidev.annotationprocessorutils.ElementUtils.*;
import org.andidev.annotationprocessorutils.JCodeModelUtils;
import org.andidev.annotationprocessorutils.ProcessingEnvironmentCodeWriter;
import org.andidev.webdriverextension.utils.GeneratorUtils;
import org.andidev.webdriverextension.bot.JUnitBot;
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
    private Set<TypeElement> pageObjectElements;
    private TypeElement extendedObjectElement;
    private JCodeModel codeModel;
    // JClasses
    private JDefinedClass siteAwareClass;
    private JClass extendedObjectClass;
    private JClass webDriverClass;
    private JClass jUnitBotClass;
    private JClass siteObjectClass;
    private Set<JClass> pageObjectClasses;

    public SiteAwareBuilder(ProcessingEnvironment processingEnv,
            TypeElement siteObjectElement, Set<TypeElement> pageObjectElements) {
        this.processingEnv = processingEnv;
        this.siteObjectElement = siteObjectElement;
        this.pageObjectElements = pageObjectElements;
    }

    public SiteAwareBuilder(ProcessingEnvironment processingEnv,
            TypeElement siteObjectElement, Set<TypeElement> pageObjectElements,
            TypeElement extendedObjectElement) {
        this.processingEnv = processingEnv;
        this.siteObjectElement = siteObjectElement;
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
        pageObjectClasses = getCodeModelRefs(pageObjectElements);
    }

    private void fields() {
        // Declare Web Driver
        if (!hasSuperDriverField()) {
            siteAwareClass.field(JMod.PRIVATE, webDriverClass, "driver");
        }

        // Declare SiteObject
        siteAwareClass.field(JMod.PUBLIC, siteObjectClass, getSiteObjectFieldName());

        // Declare PageObjects
        for (JClass pageObjectClass : pageObjectClasses) {
            siteAwareClass.field(JMod.PUBLIC, pageObjectClass, getPageObjectFieldName(pageObjectClass));
        }
    }

    private void constructors() {
//        if (isExtended()) {
//            constructorExtendedConstructors();
//        } else {
//            constructorNoArgs();
//            constructorWebDriver();
//        }
    }

    private void methods() {
//        setPageObjects();
//        getDriver();
//        setDriver();
//        setPageObjectsDriver();
    }

    private void generate() throws IOException {
        CodeWriter codeWriter = new ProcessingEnvironmentCodeWriter(processingEnv);
        codeModel.build(codeWriter);
    }

    private Set<JClass> getCodeModelRefs(Set<TypeElement> elements) {
        Set<JClass> codeModeModelRefs = new LinkedHashSet<JClass>();
        for (TypeElement pageObjectElement : pageObjectElements) {
            codeModeModelRefs.add(codeModel.ref(pageObjectElement.getQualifiedName().toString()));
        }
        return codeModeModelRefs;
    }

    private void constructorNoArgs() {
        // Create No Arguments Constructor
        JMethod method = siteAwareClass.constructor(JMod.PUBLIC);
        method.body().assign(JExpr.ref(getSiteObjectFieldName()), JExpr._new(siteObjectClass));
        method.body().invoke("setPageObjects").arg(JExpr.ref(getSiteObjectFieldName()));
    }

    private void constructorWebDriver() {
        // Create WebDriver Argument Constructor
        JMethod method = siteAwareClass.constructor(JMod.PUBLIC);
        method.param(webDriverClass, "driver");
        method.body().assign(JExpr.ref(getSiteObjectFieldName()), JExpr._new(siteObjectClass));
        method.body().invoke("setPageObjects").arg(JExpr.ref(getSiteObjectFieldName()));
        method.body().invoke("setDriver").arg(JExpr.ref("driver"));
    }

    private void constructorExtendedConstructors() {
        for (ExecutableElement constructorElement : getConstructors(extendedObjectElement)) {
            constructorExtendedConstructor(constructorElement);
        }
    }

    private void constructorExtendedConstructor(ExecutableElement constructorElement) {
        log.debug("Creating constructor: {}", getConstructorName(constructorElement));
        if (hasParameter(constructorElement, WebDriver.class)) {
            log.debug("The constructor {} has class WebDriver class as parameter", getConstructorName(constructorElement));
            JMethod method = siteAwareClass.constructor(JMod.PUBLIC);
            int i = 1;
            for (VariableElement parameter : getParameters(constructorElement)) {
                String parameterName = getParamenterName(parameter);
                JClass parameterClass = codeModel.ref(parameterName);
                String parameterFieldName = StringUtils.uncapitalize("arg" + i++);
                method.param(parameterClass, parameterFieldName);
            }
            JInvocation superInvocation = method.body().invoke("super");
            List<JVar> parameterVars = method.params();
            JCodeModelUtils.args(superInvocation, parameterVars);
            method.body().assign(JExpr.ref(getSiteObjectFieldName()), JExpr._new(siteObjectClass));
            method.body().invoke("setPageObjects").arg(JExpr.ref(getSiteObjectFieldName()));
        } else {
            log.debug("The constructor {} does not have class WebDriver class as parameter", getConstructorName(constructorElement));
            JMethod method = siteAwareClass.constructor(JMod.PUBLIC);
            int i = 1;
            for (VariableElement parameter : getParameters(constructorElement)) {
                String parameterName = getParamenterName(parameter);
                JClass parameterClass = codeModel.ref(parameterName);
                String parameterFieldName = StringUtils.uncapitalize("arg" + i++);
                method.param(parameterClass, parameterFieldName);
            }
            JInvocation superInvocation = method.body().invoke("super");
            List<JVar> parameterVars = method.params();
            JCodeModelUtils.args(superInvocation, parameterVars);
            method.body().assign(JExpr.ref(getSiteObjectFieldName()), JExpr._new(siteObjectClass));
            method.body().invoke("setPageObjects").arg(JExpr.ref(getSiteObjectFieldName()));
            method.body().invoke("setDriver").arg(JExpr.ref("driver"));
        }
    }

    private void setPageObjects() {
        // Create setPageObjects(...)
        JMethod method = siteAwareClass.method(JMod.PRIVATE, void.class, "setPageObjects");
        method.param(siteObjectClass, getSiteObjectFieldName());
        for (JClass pageObjectClass : pageObjectClasses) {
            method.body().assign(JExpr.ref(getPageObjectFieldName(pageObjectClass)), JExpr.ref(JExpr.ref(getSiteObjectFieldName()), getPageObjectFieldName(pageObjectClass)));
        }
    }

    private void getDriver() {
        // Create getDriver()
        if (hasSuperGetDriverMethod()) {
            JMethod method = siteAwareClass.method(JMod.PUBLIC, webDriverClass, "getDriver");
            method.annotate(Override.class);
            method.body()._return(JExpr.ref("driver"));
        } else {
            JMethod method = siteAwareClass.method(JMod.PUBLIC, webDriverClass, "getDriver");
            method.body()._return(JExpr.ref("driver"));
        }
    }

    private void setDriver() {
        // Create setDriver(...)
        if (hasSuperSetDriverMethod()) {
            JMethod method = siteAwareClass.method(JMod.PUBLIC, void.class, "setDriver");
            method.param(webDriverClass, "driver");
            method.annotate(Override.class);
            method.body().invoke(JExpr._super(), "setDriver").arg(JExpr.ref("driver"));
            method.body().assign(JExpr._this().ref("driver"), JExpr.ref("driver"));
            method.body().staticInvoke(jUnitBotClass, "setDriver").arg(JExpr.ref("driver"));
            method.body().invoke(JExpr.ref(getSiteObjectFieldName()), "setDriver").arg(JExpr.ref("driver"));
            method.body().invoke("setPageObjectsDriver").arg(JExpr.ref("driver"));
        } else {
            JMethod method = siteAwareClass.method(JMod.PUBLIC, void.class, "setDriver");
            method.param(webDriverClass, "driver");
            method.body().assign(JExpr._this().ref("driver"), JExpr.ref("driver"));
            method.body().invoke(JExpr.ref(getSiteObjectFieldName()), "setDriver").arg(JExpr.ref("driver"));
            method.body().invoke("setPageObjectsDriver").arg(JExpr.ref("driver"));
        }
    }

    private void setPageObjectsDriver() {
        // Create setPageObjectsDriver(...)
        JMethod method = siteAwareClass.method(JMod.PRIVATE, void.class, "setPageObjectsDriver");
        method.param(webDriverClass, "driver");
        for (JClass pageObjectClass : pageObjectClasses) {
            method.body().invoke(JExpr.ref(getPageObjectFieldName(pageObjectClass)), "setDriver").arg(JExpr.ref("driver"));
        }
    }

    private String getSiteObjectFieldName() {
        return GeneratorUtils.getName(siteObjectElement);
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

    private boolean hasSuperDriverField() {
        if (isExtended()) {
            log.debug("hasField(extendedObjectElement, \"driver\") = {}", hasField(extendedObjectElement, "driver"));
            log.debug("isPrivate(getField(extendedObjectElement, \"driver\") = {}", isPublic(getField(extendedObjectElement, "driver")));
            log.debug("isPrivate(getField(extendedObjectElement, \"driver\")) = {}", isProtected(getField(extendedObjectElement, "driver")));
            if (hasField(extendedObjectElement, "driver")
                    && (isPublic(getField(extendedObjectElement, "driver")) || isProtected(getField(extendedObjectElement, "driver")))) {
                log.debug("hasSuperDriverField");
                return true;
            }
        }
        return false;
    }

    private boolean hasSuperGetDriverMethod() {
        if (isExtended()) {
            if (hasMethod(extendedObjectElement, "getDriver()")
                    && (isPublic(getMethod(extendedObjectElement, "getDriver()")) || isProtected(getMethod(extendedObjectElement, "getDriver()")))) {
                log.debug("hasSuperGetDriverMethod");
                return true;
            }
        }
        return false;
    }

    private boolean hasSuperSetDriverMethod() {
        if (isExtended()) {
            if (hasMethod(extendedObjectElement, "setDriver(org.openqa.selenium.WebDriver)")
                    && (isPublic(getMethod(extendedObjectElement, "setDriver(org.openqa.selenium.WebDriver)")) || isProtected(getMethod(extendedObjectElement, "setDriver(org.openqa.selenium.WebDriver)")))) {
                log.debug("hasSuperSetDriverMethod");
                return true;
            }
        }
        return false;
    }
}
