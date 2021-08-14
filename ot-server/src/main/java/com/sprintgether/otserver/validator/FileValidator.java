package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.FileDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FileValidator {
    public static List<String> validate(FileDto fileDto) {
        List<String> errors = new ArrayList<>();
        if (fileDto == null) {
            errors.add("Veuillez renseigner le nom du File");
            errors.add("Veuillez renseigner l'url du File");
            errors.add("Veuillez renseigner le mimeType du File");
            errors.add("Veuillez renseigner l'extension du File");
            return errors;
        }

        if(!StringUtils.hasLength(fileDto.getName())){
            errors.add("Veuillez renseigner le nom du File");
        }
        if(!StringUtils.hasLength(fileDto.getUrl())){
            errors.add("Veuillez renseigner l'url du File");
        }

        return errors;
    }
}

