package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.annotation.MatchPassword;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@MatchPassword
@ApiModel(value = "Password reset Request", description = "The password reset request dto")
@Data
public class PasswordResetRequest {
    @NotBlank(message = "Password cannot be blank")
    @ApiModelProperty(value = "new user password", required = true, allowableValues = "NomEmpty string")
    private String password;

    @NotBlank(message = "Conformed password cannot be blank")
    @ApiModelProperty(value = "Must match the new user password. Else exception will be thrown", required = true, allowableValues = "NomEmpty String matching the password")
    private String passwordConfirmation;

    @NotBlank(message = "The token has be supplied along with a password reset request")
    @ApiModelProperty(value = "Reset token received in mail", required = true, allowableValues = "NomEmpty String")
    private String tokenString;
}
