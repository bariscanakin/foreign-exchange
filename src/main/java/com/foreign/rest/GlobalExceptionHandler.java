package com.foreign.rest;

import com.foreign.rest.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by bariscanakin on 9.3.2017.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String VALIDATION_ERROR_ON_FIELD_MSG = "Validation Error on '%s': %s";
    private static final String VALIDATION_ERROR_ON_OBJECT_MSG = "Validation Error: %s";
    private static final String HTTP_BODY_NOT_READABLE_MSG = "Http body not readable. Check if content of body is valid with respect to 'Content-Type' header";
    private static final String INTERNAL_SERVER_ERROR_MSG = "Internal Server Error";

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(BindException e) {
        logger.error("Error on validation: ", e);
        ObjectError globalError = e.getGlobalError();
        String errorMsg;
        if (globalError != null) {
            errorMsg = String.format(VALIDATION_ERROR_ON_OBJECT_MSG, globalError.getDefaultMessage());
        } else {
            FieldError error = e.getFieldError();
            errorMsg = String.format(VALIDATION_ERROR_ON_FIELD_MSG, error.getField(), error.getDefaultMessage());
        }

        return new ErrorResponse.Builder(errorMsg).build();
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleInvalidBody(HttpMessageNotReadableException e) {
        logger.error("Http body not readable: ", e);
        return new ErrorResponse.Builder(HTTP_BODY_NOT_READABLE_MSG).build();
    }

    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleContentTypeException(HttpMediaTypeNotSupportedException e) {
        logger.error("Invalid media type:", e);
        return new ErrorResponse.Builder(e.getMessage()).build();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleExpcetion(Exception e) {
        logger.error("Exception caught: ", e);
        return new ErrorResponse.Builder(INTERNAL_SERVER_ERROR_MSG).build();
    }
}
