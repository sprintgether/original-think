package com.sprintgether.otserver.validator;

import com.sprintgether.otserver.model.dto.AuthorDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AuthorValidator {
    public static List<String> validate(AuthorDto authorDto) {
        List<String> errors = new ArrayList<>();
        if(authorDto == null){
            errors.add("Veuillez renseigner le FirstName de l'auteur");
            errors.add("Veuillez renseigner le lastName de l'auteur");
            errors.add("Veuillez renseigner le title de l'auteur");
            errors.add("Veuillez renseigner l'email de l'auteur");
            errors.add("Veuillez renseigner l'institution de l'auteur");
            return errors;
        }

        if(!StringUtils.hasLength(authorDto.getFirstName())){
            errors.add("Veuillez renseigner le FirstName de l'auteur");
        }
        if(!StringUtils.hasLength(authorDto.getLastName())){
            errors.add("Veuillez renseigner le lastName de l'auteur");
        }
        if(!StringUtils.hasLength(authorDto.getTitle())){
            errors.add("Veuillez renseigner le title de l'auteur");
        }
        if(!StringUtils.hasLength(authorDto.getEmail())){
            errors.add("Veuillez renseigner l'email de l'auteur");
        }
        if(!StringUtils.hasLength(authorDto.getInstitution())){
            errors.add("Veuillez renseigner l'institution de l'auteur");
        }

        return errors;
    }
}
