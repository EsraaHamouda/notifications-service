package com.notification.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    EnumValueNotFound("001", "No enum found, please send the correct type name"),
    ErrorOccurred("500", "Something went wrong"),
    NotTemplateFound("404", "wrong template type is sent"),
    UserNotFound("404", "User is not found"),
    GroupNotFound("404", "Group is not found"),
    UserIdNotProvided("400", "User id not provided"),
    GroupIdNotProvided("400", "Group is not provided"),
    ProviderNotSupported("404", "Notification provider not supported");
    ErrorCode(){}
    ErrorCode(String code,String message ){
        this.code = code;
        this.message = message;
    }


    private String code;

    private String message;


    @Override
    public String toString() {
        return "ErrorCode{" +
                "code='" + code + '\'' +
                ", description='" + message + '\'' +
                '}';
    }

}
