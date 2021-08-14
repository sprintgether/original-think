package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.StudyTripDto;

import java.util.ArrayList;
import java.util.List;

public class StudyTripValidator {
    public static List<String> validate(StudyTripDto studyTripDto) {
        List<String> errors = new ArrayList<>();
        if (studyTripDto == null) {
            errors.add("Veuillez renseigner le mentorName de StudyTrip");
        }

        return errors;
    }
}
