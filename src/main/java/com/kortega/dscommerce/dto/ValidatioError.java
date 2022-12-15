package com.kortega.dscommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidatioError extends CustomError{

    private List <FielMenssage> errors = new ArrayList<>();

    public ValidatioError(Instant timestamp, Integer status, String error, String path) {
        super(timestamp, status, error, path);
    }

    public List<FielMenssage> getErros() {
        return errors;
    }

    public void addError (String fieldName, String message){
        errors.add(new FielMenssage(fieldName, message));

    }
}
