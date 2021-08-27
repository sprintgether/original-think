package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Talk;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TalkDto {
    private String studyLevel;

    private String theme;
    private String domain;
    private String description;
    private String abstracts;

    public static TalkDto fromEntity(Talk talk) {
        if (talk == null) {
            return null;
        }

        return TalkDto.builder()
                .studyLevel(talk.getStudyLevel())
                .theme(talk.getTheme())
                .domain(talk.getDomain())
                .description(talk.getDescription())
                .abstracts(talk.getAbstracts())
                .build();
    }

    public static Talk toEntity(TalkDto talkDto) {
        if (talkDto == null){
            return null;
        }

        Talk talk = new Talk();
        talk.setStudyLevel(talkDto.studyLevel);
        talk.setTheme(talkDto.theme);
        talk.setDomain(talkDto.domain);
        talk.setDescription(talkDto.description);
        talk.setAbstracts(talkDto.abstracts);

        return talk;
    }
}
