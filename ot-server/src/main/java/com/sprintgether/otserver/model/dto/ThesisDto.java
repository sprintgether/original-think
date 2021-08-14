package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Thesis;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThesisDto {
    private String documentLink;
    private String grade;

    public static ThesisDto fromEntity(Thesis thesis){
        if (thesis == null){
            return null;
        }

        return ThesisDto.builder()
                .documentLink(thesis.getDocumentLink())
                .grade(thesis.getGrade())
                .build();
    }

    public static Thesis toEntity(ThesisDto thesisDto){
        if (thesisDto == null){
            return null;
        }

        Thesis thesis = new Thesis();
        thesis.setDocumentLink(thesisDto.getDocumentLink());
        thesis.setGrade(thesisDto.getGrade());

        return thesis;
    }
}
