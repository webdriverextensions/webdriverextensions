package org.webdriverextensions.internal.generator;

import com.sun.codemodel.CodeWriter;
import com.sun.codemodel.JPackage;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.processing.ProcessingEnvironment;
import org.apache.commons.lang3.StringUtils;

public class ProcessingEnvironmentCodeWriter extends CodeWriter {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProcessingEnvironmentCodeWriter.class);

    ProcessingEnvironment processingEnv;
    OutputStream outputStream;

    public ProcessingEnvironmentCodeWriter(ProcessingEnvironment processingEnv) {
        this.processingEnv = processingEnv;
    }

    @Override
    public OutputStream openBinary(JPackage pkg, String fileName) throws IOException {
        outputStream = processingEnv.getFiler().createSourceFile(pkg.name() + "." + StringUtils.removeEnd(fileName, ".java")).openOutputStream();
        return outputStream;
    }

    @Override
    public void close() throws IOException {
        outputStream.close();
    }

}
