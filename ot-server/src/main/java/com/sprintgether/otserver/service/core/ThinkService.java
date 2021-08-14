package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.ThesisDto;
import com.sprintgether.otserver.model.dto.ThinkDto;

import java.util.List;

public interface ThinkService {
    ThinkDto save(ThinkDto thinkDto);

    ThinkDto findById(String id);

    ThinkDto findByJournal(String journal);

    List<ThinkDto> findAll();

    void delete(String id);
}
