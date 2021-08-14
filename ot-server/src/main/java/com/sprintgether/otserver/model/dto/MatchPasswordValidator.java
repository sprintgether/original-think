package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.annotation.MatchPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, PasswordResetRequest> {
    private Boolean allowNull;

    @Override
    public void initialize(MatchPassword contraintAnnotation){
        allowNull = contraintAnnotation.allowNull();
    }

    @Override
    public boolean isValid(PasswordResetRequest passwordResetRequest, ConstraintValidatorContext constraintValidatorContext){
        String password = passwordResetRequest.getPassword();
        String conformPassword = passwordResetRequest.getPasswordConfirmation();
        if (allowNull){
            return null == password && null == conformPassword;
        }
        return password.equals(conformPassword);
    }
}
