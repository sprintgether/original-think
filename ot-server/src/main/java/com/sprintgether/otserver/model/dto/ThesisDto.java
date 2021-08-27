package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Thesis;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThesisDto {
    private String documentLink;
    private String grade;

    protected String theme;
    protected String domain;
    protected String description;
    protected String abstracts;

    public static ThesisDto fromEntity(Thesis thesis){
        if (thesis == null){
            return null;
        }

        return ThesisDto.builder()
                .documentLink(thesis.getDocumentLink())
                .grade(thesis.getGrade())
                .theme(thesis.getTheme())
                .domain(thesis.getDomain())
                .description(thesis.getDescription())
                .abstracts(thesis.getAbstracts())
                .build();
    }

    public static Thesis toEntity(ThesisDto thesisDto){
        if (thesisDto == null){
            return null;
        }

        Thesis thesis = new Thesis();
        thesis.setDocumentLink(thesisDto.getDocumentLink());
        thesis.setGrade(thesisDto.getGrade());
        thesis.setTheme(thesisDto.getTheme());
        thesis.setDomain(thesisDto.getDomain());
        thesis.setDescription(thesis.getDescription());
        thesis.setAbstracts(thesis.getAbstracts());

        return thesis;
    }
}
