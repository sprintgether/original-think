package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.FileDto;
import com.sprintgether.otserver.model.dto.KeyWordDto;

import java.util.ArrayList;
import java.util.List;

public class KeyWordValidator {
    public static List<String> validate(KeyWordDto keyWordDto) {
        List<String> errors = new ArrayList<>();
        if (keyWordDto == null) {
            errors.add("Veuillez renseigner la valeur du KeyWord");
        }

        return errors;
    }
}
