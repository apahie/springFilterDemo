package com.example.demo.annotations;

import com.example.demo.SampleType;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Sample {
    @AliasFor("type")
    SampleType value() default SampleType.DEFAULT;

    @AliasFor("value")
    SampleType type() default SampleType.DEFAULT;
}
