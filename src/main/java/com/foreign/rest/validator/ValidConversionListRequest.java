package com.foreign.rest.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bariscanakin on 9.3.2017.
 */
@Constraint(validatedBy = ConversionListRequestValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidConversionListRequest {
    String message() default "id or date parameter must be given";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
