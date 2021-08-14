package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserValidator {
    public static List<String> validate(UserDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner l'email du Think");
        }

        return errors;
    }
}
