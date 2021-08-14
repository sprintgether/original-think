package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.RoleDto;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {
    public static List<String> validate(RoleDto roleDto) {
        List<String> errors = new ArrayList<>();
        if (roleDto == null) {
            errors.add("Veuillez renseigner le nom du rôle");
        }

        return errors;
    }
}
