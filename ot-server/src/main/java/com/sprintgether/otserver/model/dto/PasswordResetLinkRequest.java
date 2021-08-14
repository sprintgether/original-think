package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.annotation.ValidEmail;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PasswordResetLinkRequest {
    //@ValidEmail
    @NotBlank(message = "Le mail ne doit pas être vide")
    private String email;
}
