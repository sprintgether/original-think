package com.sprintgether.otserver.model.payload;

import com.sprintgether.otserver.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {

    @NotBlank(message = "L'email ne doit pas être vide")
    private String email;

    @NotBlank(message = "Le mot de passe ne doit pas être vide")
    @Length(min = 4, message = "Il faut un mot de passe d'au moins 4 caractères.")
    private String password;

}
