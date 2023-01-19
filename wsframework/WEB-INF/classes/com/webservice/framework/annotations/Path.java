package com.webservice.framework.annotations;
import java.lang.annotation.*;
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE}) // TYPE for class
public @interface Path{
public String value();
}

