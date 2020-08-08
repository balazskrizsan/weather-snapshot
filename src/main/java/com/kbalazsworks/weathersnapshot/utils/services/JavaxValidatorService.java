package com.kbalazsworks.weathersnapshot.utils.services;

import org.springframework.stereotype.Service;

import javax.validation.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Logger;

@Service
public class JavaxValidatorService<T>
{
    private Logger logger = Logger.getLogger("JavaxValidatorService");

    public void validateWithConsoleLog(T entity)
    {
        ValidatorFactory factory   = Validation.buildDefaultValidatorFactory();
        Validator        validator = factory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        for (ConstraintViolation<T> violation : violations)
        {
            logger.info("Validation error: " + violation.getMessage());
        }
    }

    public void validate(T entity)
    {
        ValidatorFactory factory   = Validation.buildDefaultValidatorFactory();
        Validator        validator = factory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        ArrayList<ValidationException> validationException = new ArrayList<>();

        for (ConstraintViolation<T> violation : violations)
        {
            validationException.add(new ValidationException(violation.getMessage()));
            logger.info("Validation error: " + violation.getMessage());
        }

        if (validationException.size() > 0)
        {
            throw validationException.get(0);
        }
    }

    public void arrayValidateWithConsoleLog(ArrayList<T> entities)
    {
        for (T entity : entities)
        {
            validateWithConsoleLog(entity);
        }
    }
}
