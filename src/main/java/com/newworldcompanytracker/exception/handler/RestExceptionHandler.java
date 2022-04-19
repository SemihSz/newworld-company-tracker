package com.newworldcompanytracker.exception.handler;

import com.newworldcompanytracker.exception.AuthenticationException;
import com.newworldcompanytracker.model.ExceptionResponse;
import com.newworldcompanytracker.model.RestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

/**
 * Created by Semih, 1.09.2021
 * <p>github: <a href="https://github.com/SemihSz ">
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@RequiredArgsConstructor
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<RestResponse<ExceptionResponse>> authenticationRegister(AuthenticationException ex) {

        final ExceptionResponse response = new ExceptionResponse();
        response.setErrorCode(535); // Authentication Exception Code
        response.setErrorMessage(ex.getMessage());
        response.setTimestamp(LocalDateTime.now());

        return ResponseEntity.ok(new RestResponse<ExceptionResponse>(500, response));
    }

}
