package com.infy.infyinterns.utility;

import com.infy.infyinterns.exception.InfyInternException;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggingAspect.class.getName());

    @AfterThrowing(
        pointcut = "execution(* com.infy.infyinterns.service.ProjectAllocationServiceImpl.*(..))",
        throwing = "exception"
    )
    public void logServiceException(InfyInternException exception) {
        LOGGER.log(Level.SEVERE, "Exception occurred: {0}", exception.getMessage());
    }
}
