package com.sprintgether.otserver.annotation;

import com.sprintgether.otserver.model.dto.MatchPasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;


@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchPasswordValidator.class)
@Documented
public @interface MatchPassword {
    String message() default "Les mots de passe doivent être identiques";
    Class<?>[] groups() default {};
    boolean allowNull() default false;
    Class<? extends Payload>[] payload() default {};
}
