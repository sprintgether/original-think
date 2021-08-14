package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Think;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ThinkDto {
    private String documentLink;
    private String journal;

    public static ThinkDto fromEntity(Think think){
        if(think == null){
            return null;
        }

        return ThinkDto.builder()
                .documentLink(think.getDocumentLink())
                .journal(think.getJournal())
                .build();
    }

    public static Think toEntity(ThinkDto thinkDto){
        if(thinkDto == null){
            return null;
        }

        Think think = new Think();

        think.setDocumentLink(thinkDto.documentLink);
        think.setJournal(thinkDto.journal);

        return think;
    }
}
