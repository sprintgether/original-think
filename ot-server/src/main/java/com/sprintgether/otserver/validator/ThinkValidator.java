package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.TalkDto;
import com.sprintgether.otserver.model.dto.ThinkDto;

import java.util.ArrayList;
import java.util.List;

public class ThinkValidator {
    public static List<String> validate(ThinkDto dto) {
        List<String> errors = new ArrayList<>();
        if (dto == null) {
            errors.add("Veuillez renseigner le Journal du Think");
        }

        return errors;
    }
}
