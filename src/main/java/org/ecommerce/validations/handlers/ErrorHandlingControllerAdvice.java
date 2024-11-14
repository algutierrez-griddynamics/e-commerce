package org.ecommerce.validations.handlers;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.ecommerce.exceptions.MappingException;
import org.ecommerce.validations.ValidationErrorResponse;
import org.ecommerce.validations.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Objects;


@ControllerAdvice
class ErrorHandlingControllerAdvice {

    final String CLIENT_WRONG_REQUEST = "The client request is invalid";


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            error.getViolations()
                    .add(new Violation(violation.getPropertyPath().toString(), violation.getMessage()));
        }
        return error;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            int dot = fieldError.getField().indexOf('.') + 1;
            int defaultMessageLength = Objects.requireNonNull(fieldError.getField()).length();

            error.getViolations().add(new Violation
                    (
                            fieldError.getField().substring(Math.max(dot, 0), defaultMessageLength)
                            , fieldError.getDefaultMessage()
                    ));
        }
        return error;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onHttpMessageNotReadableException(HttpMessageNotReadableException e) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getViolations()
                .add(new Violation(CLIENT_WRONG_REQUEST, e.getCause() != null ?
                        e.getCause().getMessage() : "" ));
        return error;
    }

    @ExceptionHandler(MappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onConstraintViolation(MappingException mpe) {
        ValidationErrorResponse error = new ValidationErrorResponse();
        error.getViolations().add(
                new Violation(mpe.getMapperClass(), mpe.getMessage())
        );
        return error;
    }
}

