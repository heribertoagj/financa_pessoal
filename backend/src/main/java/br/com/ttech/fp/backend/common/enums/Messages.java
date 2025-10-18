package br.com.ttech.fp.backend.common.enums;

import lombok.Getter;

@Getter
public enum Messages {

    AUTHORIZATION_TOKEN_IS_INVALID("Authorization token is invalid"),

    ERROR_WHILE_GENERATE_TOKEN("Error while generate token"),

    ID_IS_REQUIRED("Id is required"),

    OPERATION_EXECUTED_SUCCESSFULLY ("Operation executed successfully"),

    REGISTERS_NOT_FOUND("Registers not found"),
    REQUIRED_PARAMETER_IS_MISSING("Required parameter is missing"),
    REVENUE_NOT_FOUND("Revenue not found"),
    REVENUE_TYPE_NOT_FOUND("Revenue type not found"),

    USER_ID_IS_REQUIRED("User identification is required"),
    USERNAME_NOT_AVAILABLE("Username not available"),
    USERNAME_OR_PASSWORD_IS_INVALID("Username or password is invalid");

    private final String message;

    Messages(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
