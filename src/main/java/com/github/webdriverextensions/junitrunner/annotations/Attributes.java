/*
 * 
 */
package com.github.webdriverextensions.junitrunner.annotations;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// TODO: Auto-generated Javadoc
/**
 * The Interface Attributes.
 */
@Retention(RetentionPolicy.RUNTIME)
@Inherited

public @interface Attributes {

    /**
     * Author.
     *
     * @return the string
     */
    String Author();

    /**
     * Description.
     *
     * @return the string
     */
    String Description();

    /**
     * Category.
     *
     * @return the string
     */
    String Category();
}
