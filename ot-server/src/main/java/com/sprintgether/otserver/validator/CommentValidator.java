package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.CommentDto;

import java.util.ArrayList;
import java.util.List;

public class CommentValidator {
    public static List<String> validate(CommentDto commentDto) {
        List<String> errors = new ArrayList<>();
        if (commentDto == null) {
            errors.add("Veuillez renseigner le content du commentaire");
        }

        return errors;
    }

}
