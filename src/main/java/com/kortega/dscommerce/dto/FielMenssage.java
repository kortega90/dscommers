package com.kortega.dscommerce.dto;

public class FielMenssage {
    private String fieldName;
    private String message;

    public FielMenssage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getMessage() {
        return message;
    }
}
