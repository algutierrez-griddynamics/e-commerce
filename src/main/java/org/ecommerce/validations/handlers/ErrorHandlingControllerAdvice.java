package org.ecommerce.validations.handlers;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.ecommerce.exceptions.*;
import org.ecommerce.validations.ValidationErrorResponse;
import org.ecommerce.validations.Violation;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;


@ControllerAdvice
class ErrorHandlingControllerAdvice {

    final String CLIENT_WRONG_REQUEST = "The client request is invalid";
    static ValidationErrorResponse error = new ValidationErrorResponse();


    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onConstraintValidationException(ConstraintViolationException e) {
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
        error.getViolations()
                .add(new Violation(CLIENT_WRONG_REQUEST, e.getCause() != null ?
                        e.getCause().getMessage() : "" ));
        return error;
    }

    @ExceptionHandler(MappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse onConstraintViolation(MappingException mpe) {
        error.getViolations().add(
                new Violation(mpe.getMapperClass(), mpe.getMessage())
        );
        return error;
    }

    @ExceptionHandler({PaymentDetailsException.class, BillingInformationException.class,
    OutOfStockException.class, ShippingInformationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorResponse handlePaymentDetailsException(Exception e) {
        error.getViolations().add(new Violation(CLIENT_WRONG_REQUEST, e.getMessage()));
        return error;
    }

    @ExceptionHandler({EntityNotFound.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ValidationErrorResponse handleNotFoundExceptions(EntityNotFound entityNotFound) {
        error.getViolations().add(new Violation(entityNotFound.getEntity(), entityNotFound.getMessage()));
        return error;
    }

    @ExceptionHandler(JsonProcessingException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ValidationErrorResponse handleJsonProcessingException(JsonProcessingException jsonProcessingException) {
        error.getViolations().add(new Violation(JsonProcessingException.class.getName(), jsonProcessingException.getMessage()));
        return error;
    }


}

