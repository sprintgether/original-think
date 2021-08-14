package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.ThesisDto;

import java.util.List;

public interface ThesisService {
    ThesisDto save(ThesisDto thesisDto);

    ThesisDto findById(String id);

    ThesisDto findByGrade(String grade);

    List<ThesisDto> findAll();

    void delete(String id);
}
