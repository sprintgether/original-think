package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Talk;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TalkDto {
    private String studyLevel;

    public static TalkDto fromEntity(Talk talk){
        if (talk == null){
            return null;
        }

        return TalkDto.builder()
                .studyLevel(talk.getStudyLevel())
                .build();
    }

    public static Talk toEntity(TalkDto talkDto){
        if (talkDto == null){
            return null;
        }

        Talk talk = new Talk();
        talk.setStudyLevel(talkDto.studyLevel);

        return talk;
    }
}
