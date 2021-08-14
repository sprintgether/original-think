package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.TalkDto;
import com.sprintgether.otserver.model.dto.ThesisDto;

import java.util.ArrayList;
import java.util.List;

public class ThesisValidator {
    public static List<String> validate(ThesisDto thesisDto) {
        List<String> errors = new ArrayList<>();
        if (thesisDto == null) {
            errors.add("Veuillez renseigner le grade du thesis");
        }

        return errors;
    }
}
