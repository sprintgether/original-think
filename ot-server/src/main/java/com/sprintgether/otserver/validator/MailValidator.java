package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.KeyWordDto;
import com.sprintgether.otserver.model.dto.MailDto;

import java.util.ArrayList;
import java.util.List;

public class MailValidator {
    public static List<String> validate(MailDto mailDto) {
        List<String> errors = new ArrayList<>();
        if (mailDto == null) {
            errors.add("Veuillez renseigner le content du mail");
        }

        return errors;
    }
}
