package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Think;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class ThinkDto {

    private String documentLink;
    private String journal;

    // Article info
    private String theme;
    private String domain;
    private String description;
    private String abstracts;
    private Boolean isPublished;
    private Instant publishedAt;

    public static ThinkDto fromEntity(Think think){
        if(think == null){
            return null;
        }

        return ThinkDto.builder()
                .documentLink(think.getDocumentLink())
                .journal(think.getJournal())
                .domain(think.getDomain())
                .description(think.getDescription())
                .abstracts(think.getAbstracts())
                .isPublished(think.getIsPublished())
                .publishedAt(think.getPublishedAt())
                .build();
    }

    public static Think toEntity(ThinkDto thinkDto){
        if(thinkDto == null){
            return null;
        }

        Think think = new Think();

        think.setDocumentLink(thinkDto.documentLink);
        think.setJournal(thinkDto.journal);
        think.setDomain(thinkDto.getDomain());
        think.setDescription(thinkDto.getDescription());
        think.setAbstracts(thinkDto.getAbstracts());
        think.setIsPublished(thinkDto.getIsPublished());
        think.setPublishedAt(thinkDto.getPublishedAt());
        return think;
    }
}
