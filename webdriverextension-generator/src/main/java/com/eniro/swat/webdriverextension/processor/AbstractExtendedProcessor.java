package com.eniro.swat.webdriverextension.processor;

import java.io.IOException;
import java.io.Writer;
import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

    public abstract class AbstractExtendedProcessor extends AbstractProcessor {

    protected RoundEnvironment roundEnvironment;
    private Messager messager;
    protected boolean debug = true;

    protected void error(String msg) {
        messager.printMessage(Diagnostic.Kind.ERROR, msg);
    }

    protected void warn(String msg) {
        messager.printMessage(Diagnostic.Kind.WARNING, msg);
    }

    protected void debug(String msg) {
        if (debug) {
            messager.printMessage(Diagnostic.Kind.NOTE, msg);
        }
    }

    protected void init(RoundEnvironment roundEnvironment) {
        this.roundEnvironment = roundEnvironment;
        this.messager = processingEnv.getMessager();
    }
    
    protected void generateClass(String templateName, VelocityContext metaData, String filePackage, String fileName) {
        // Get Velocity Template
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty("runtime.log.logsystem.class", "org.apache.velocity.runtime.log.SystemLogChute");
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath"); 
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();
        Template template = velocityEngine.getTemplate(templateName);
                
        try {
            // Get File Writer
            JavaFileObject file = processingEnv.getFiler().createSourceFile(filePackage + "." + fileName);
            Writer writer = file.openWriter();

            // Write to file
            template.merge(metaData, writer);
            writer.close();
        } catch (IOException ex) {
            error("Generation ERROR, IOException: " + ex);
        }
    }
    
    
}
