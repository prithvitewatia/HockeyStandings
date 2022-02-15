package com.example.HockeyStandings.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApiErrorResponse implements Serializable {
    private Integer status;
    private String path;
    private String message,exception;
    private LocalDate timestamp;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ApiError> errors;
    private ApiErrorResponse(Integer status,String path,String message,String exception){
        this.status=status;
        this.path=path;
        this.message=message;
        this.exception=exception;
    }
    public static ApiErrorResponse valueOf(Integer status,String path,String message,String exception){
        return new ApiErrorResponse(status,path,message,exception);
    }
    public ApiErrorResponse(){}

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public LocalDate getTimestamp() {
        if(timestamp==null){
            timestamp=LocalDate.now();
        }
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public List<ApiError> getErrors() {
        if(errors==null){
            errors=new ArrayList<>();
        }
        return errors;
    }

    public void setErrors(List<ApiError> errors) {
        this.errors = errors;
    }
}
