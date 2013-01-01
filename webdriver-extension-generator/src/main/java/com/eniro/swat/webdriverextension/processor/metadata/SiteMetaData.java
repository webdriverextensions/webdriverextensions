package com.eniro.swat.webdriverextension.processor.metadata;

import java.util.List;

public class SiteMetaData {
    private String packageName;
    private String className;
    private String fieldName;

    @Override
    public String toString() {
        return "SiteClassMetaData{" + "packageName=" + packageName + ", className=" + className + ", fieldName=" + fieldName + '}';
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
