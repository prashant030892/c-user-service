package com.centime.userservice.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to log method params. An aspect has been written
 * which checks for all the methods which have this annotation and logs the
 * method params.
 * 
 * @author prkala
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogMethodParam {

}
