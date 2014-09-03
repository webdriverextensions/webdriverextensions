package com.github.webdriverextensions.datalaoder;

import java.util.Properties;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

public class DataLoader implements TestRule {

    private final Object target;

    public DataLoader(Object target) {
        this.target = target;
    }

    @Override
    public Statement apply(final Statement base, final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                DataContext.init();

                DataContext.loadDataFromPropertyFile("data.properties");
                System.out.println("data = " + DataContext.getAllData());

                Data classAnnotation = target.getClass().getAnnotation(Data.class);
                if (classAnnotation != null) {
                    for (String filename : classAnnotation.value()) {
                        System.out.println("classAnnotation value = " + filename);
                        DataContext.loadDataFromPropertyFile(filename);
                        System.out.println("data = " + DataContext.getAllData());
                    }
                }

                Data methodAnnotation = description.getAnnotation(Data.class);
                if (methodAnnotation != null) {
                    for (String filename : methodAnnotation.value()) {
                        System.out.println("methodAnnotation value = " + filename);
                        DataContext.loadDataFromPropertyFile(filename);
                        System.out.println("data = " + DataContext.getAllData());
                    }
                }

                try {
                    base.evaluate();
                } finally {
                    DataContext.destroy();
                }
            }
        };
    }
}
