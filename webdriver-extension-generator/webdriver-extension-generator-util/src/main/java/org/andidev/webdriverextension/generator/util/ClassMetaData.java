package org.andidev.webdriverextension.generator.util;

public class ClassMetaData implements Comparable<Object> {

    private String packageName;
    private String className;
    private String fieldName;

    @Override
    public String toString() {
        return "PageClassMetaData{" + "packageName=" + packageName + ", className=" + className + ", fieldName=" + fieldName + '}';
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

    @Override
    public int compareTo(Object t) {
        ClassMetaData comparedTo = (ClassMetaData) t;
        if (this.packageName.compareTo(comparedTo.packageName) != 0) {
            return this.packageName.compareTo(comparedTo.packageName);
        }
        return this.className.compareTo(comparedTo.className);
    }
}
