package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.SupervisorDto;

import java.util.ArrayList;
import java.util.List;

public class SupervisorValidator {
    public static List<String> validate(SupervisorDto supervisorDto) {
        List<String> errors = new ArrayList<>();
        if (supervisorDto == null) {
            errors.add("Veuillez renseigner l'email du superviseur");
        }

        return errors;
    }
}
