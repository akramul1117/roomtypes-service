/*
 *
 *  * --------------------------------------------------------------------------
 *  *   Copyright (C) 1997 - 2019TeamAmerica
 *  * --------------------------------------------------------------------------
 *  *
 *  * NOTICE:  All information contained herein is, and remains the property of TeamAmeirca and its suppliers, if any.
 *  * The intellectual and technical concepts contained herein are proprietary to TeamAmeirca and its suppliers and are protected by trade secret or copyright law.
 *  * Dissemination of this information or reproduction of this material is strictly forbidden unless prior written permission is obtained from TeamAmeirca.
 *  *
 *  *
 *
 */

package com.teamamerica.tourbot.roomtypes.service.exceptionHandler;





import com.teamamerica.tourbot.roomtypes.service.exception.DataNotFoundException;
import com.teamamerica.tourbot.roomtypes.service.exception.ExceptionResponse;
import com.teamamerica.tourbot.roomtypes.service.utils.RoomTypeUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Created by akadir on 6/2/17.
 */
@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(DataNotFoundException.class)
    public final ResponseEntity<Object> dataNotFoundExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(DataAccessException.class)
    public final ResponseEntity<Object> handleAllDataAccessExceptions(DataAccessException ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Data Not Found", request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {

        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));

        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    protected final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), RoomTypeUtils.VALIDATION_FAILED, ex.getBindingResult().getAllErrors().toString());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }






}
