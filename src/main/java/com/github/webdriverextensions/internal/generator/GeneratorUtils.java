package com.github.webdriverextensions.internal.generator;

import com.github.webdriverextensions.WebPage;
import com.github.webdriverextensions.WebRepository;
import com.github.webdriverextensions.WebSite;
import com.sun.codemodel.JAnnotationArrayMember;
import com.sun.codemodel.JAnnotationUse;
import com.sun.codemodel.JFieldVar;
import java.util.List;
import java.util.Map;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class GeneratorUtils {

    public static void error(String msg, ProcessingEnvironment processingEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, msg);
    }

    public static void warn(String msg, ProcessingEnvironment processingEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, msg);
    }

    public static void info(String msg, ProcessingEnvironment processingEnv) {
        processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, msg);
    }

    public static boolean isSiteClass(Element element, ProcessingEnvironment processingEnv) {
        TypeMirror type = element.asType();
        TypeMirror siteClassType = processingEnv.getElementUtils().getTypeElement(WebSite.class.getCanonicalName()).asType();
        return processingEnv.getTypeUtils().isAssignable(type, siteClassType) || ((TypeElement)element).getSuperclass().toString().equals("GeneratedWebSite");
    }

    public static boolean isPageClass(Element element, ProcessingEnvironment processingEnv) {
        TypeMirror type = element.asType();
        TypeMirror pageClassType = processingEnv.getElementUtils().getTypeElement(WebPage.class.getCanonicalName()).asType();
        return processingEnv.getTypeUtils().isAssignable(type, pageClassType);
    }

    public static boolean isRepositoryClass(Element element, ProcessingEnvironment processingEnv) {
        TypeMirror type = element.asType();
        TypeMirror repositoryClassType = processingEnv.getElementUtils().getTypeElement(WebRepository.class.getCanonicalName()).asType();
        return processingEnv.getTypeUtils().isAssignable(type, repositoryClassType);
    }

    public static String getFieldName(Element element) {
        String className = element.getSimpleName().toString();
        return StringUtils.uncapitalize(className);
    }

    public static boolean hasFindByAnnotation(TypeElement element) {
        return getFindByAnnotation(element) != null;
    }

    public static AnnotationMirror getFindByAnnotation(TypeElement element) {
        for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
            if (annotationMirror.getAnnotationType().toString().equals("org.openqa.selenium.support.FindBy")) {
                return annotationMirror;
            }
        }
        return null;
    }

    public static boolean hasFindBysAnnotation(TypeElement element) {
        return getFindBysAnnotation(element) != null;
    }

    public static AnnotationMirror getFindBysAnnotation(TypeElement element) {
        for (AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
            if (annotationMirror.getAnnotationType().toString().equals("org.openqa.selenium.support.FindBys")) {
                return annotationMirror;
            }
        }
        return null;
    }

    public static void annotateFieldWithFindBysAnnotation(JFieldVar field, AnnotationMirror findBys) {
        JAnnotationUse findBysAnnotation = field.annotate(FindBys.class);
        JAnnotationArrayMember findBysAnnotationValues = findBysAnnotation.paramArray("value");
        for (AnnotationMirror findBy : (List<AnnotationMirror>) findBys.getElementValues().values().iterator().next().getValue() ) {
            JAnnotationUse findByAnnotation = findBysAnnotationValues.annotate(FindBy.class);
            setFindByAnnotationValues(findByAnnotation, findBy);
        }
    }

    public static void annotateFieldWithFindByAnnotation(JFieldVar field, AnnotationMirror findBy) {
        JAnnotationUse annotation = field.annotate(FindBy.class);
        setFindByAnnotationValues(annotation, findBy);
    }

    public static void setFindByAnnotationValues(JAnnotationUse annotation, AnnotationMirror findBy) {
        for (Map.Entry<? extends ExecutableElement, ? extends AnnotationValue> entry : findBy.getElementValues().entrySet()) {
            if (entry.getKey().getSimpleName().toString().equals("how")) {
                annotation.param(entry.getKey().getSimpleName().toString(), How.valueOf(entry.getValue().getValue().toString().replaceAll("$\"", "").replaceAll("\"^", "")));
            } else {
                annotation.param(entry.getKey().getSimpleName().toString(), entry.getValue().getValue().toString().replaceAll("$\"", "").replaceAll("\"^", ""));
            }
        }
    }

}
