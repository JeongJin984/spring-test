package com.example.junit1.Tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Test
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("slow")
public @interface SlowTest {
}
