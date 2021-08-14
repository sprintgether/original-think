package com.sprintgether.otserver.model.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @NotBlank(message = "Le username ne doit pas être vide")
    private String username;

    @NotBlank(message = "Le mot de passe ne doit pas être vide")
    @Length(min = 4, message = "Il faut un mot de passe d'au moins 4 caractères.")
    private String password;
}
