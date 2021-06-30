package com.notification.exception;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"stackTrace", "cause", "suppressed", "localizedMessage"})
public class CustomException extends RuntimeException {


    @Getter
    @Setter
    ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }


}
