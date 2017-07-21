//package edu.ucsd.lecture2;
package week2;

import java.lang.annotation.*;

@Retention(value=RetentionPolicy.RUNTIME)
@Illustrate( {Illustrate.Feature.annotation,
              Illustrate.Feature.enumeration } )
public @interface Illustrate {
    enum Feature {
        annotation, enumeration, forLoop,
        generics, autoboxing, varargs;

       @Override
        public String toString() {
           return "the " + name() + " feature";
        }
    };
    Feature[] value() default {Feature.annotation};
}



