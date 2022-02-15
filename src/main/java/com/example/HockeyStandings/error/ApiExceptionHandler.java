package com.example.HockeyStandings.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request){
        return prepareRestException(ex,request, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex,
            HttpHeaders headers,
            HttpStatus status,WebRequest request){
        return prepareRestException(ex,request,HttpStatus.BAD_REQUEST);
    }
    private ResponseEntity<Object> prepareRestException(Exception ex,WebRequest request,HttpStatus status){
        logger.info(ex.getClass().getName());
        String error=ex.getLocalizedMessage();
        if(ex.getCause()!=null){
            error+=String.format(". %s",ex.getCause().getMessage());
        }
        else if(StringUtils.isEmpty(error)){
            error="message not available";
        }
        final ApiErrorResponse response=ApiErrorResponse.valueOf(status.value(),
                getPath(request),error,ex.getClass().getName());
        return new ResponseEntity<>(response,new org.springframework.http.HttpHeaders(),status);
    }
    private String getPath(WebRequest request){
        return ((ServletWebRequest) request)
                .getRequest()
                .getRequestURI();
    }
}
