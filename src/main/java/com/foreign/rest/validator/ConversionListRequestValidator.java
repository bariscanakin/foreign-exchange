package com.foreign.rest.validator;

import com.foreign.rest.model.ConversionListRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by bariscanakin on 9.3.2017.
 */
public class ConversionListRequestValidator implements ConstraintValidator<ValidConversionListRequest, ConversionListRequest> {
    @Override
    public void initialize(ValidConversionListRequest validConversionListRequest) {
    }

    @Override
    public boolean isValid(ConversionListRequest request, ConstraintValidatorContext constraintValidatorContext) {
        if (request == null) {
            return true;
        }

        if (request.getId() == null && request.getDate() == null) {
            return false;
        }

        return true;
    }
}
