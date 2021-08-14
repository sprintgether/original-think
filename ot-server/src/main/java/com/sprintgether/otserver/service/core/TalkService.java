package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.TalkDto;

import java.util.List;

public interface TalkService {
    TalkDto save(TalkDto talkDto);

    TalkDto findById(String id);

    TalkDto findByStudyLevel(String studyLevel);

    List<TalkDto> findAll();

    void delete(String id);
}
