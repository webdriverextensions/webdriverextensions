package com.eniro.swat.webdriverextension.processor.metadata;

public class SiteTestBaseMetaData {
    private String packageName;
    private String className;

    @Override
    public String toString() {
        return "SiteTestMetaData{" + "packageName=" + packageName + ", className=" + className + '}';
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
}
