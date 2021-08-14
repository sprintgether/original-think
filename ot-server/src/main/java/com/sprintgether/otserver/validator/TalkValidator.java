package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.StudyTripDto;
import com.sprintgether.otserver.model.dto.TalkDto;

import java.util.ArrayList;
import java.util.List;

public class TalkValidator {
    public static List<String> validate(TalkDto talkDto) {
        List<String> errors = new ArrayList<>();
        if (talkDto == null) {
            errors.add("Veuillez renseigner le studyLevel du Talk");
        }

        return errors;
    }
}
