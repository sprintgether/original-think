package com.sprintgether.otserver.service.core;


import com.sprintgether.otserver.model.dto.ArticleDto;
import com.sprintgether.otserver.model.dto.StudyTripDto;

import java.util.List;

public interface StudyTripService {
    StudyTripDto save(StudyTripDto studyTripDto);

    StudyTripDto findById(String id);

    StudyTripDto findByLocality(String locality);

    StudyTripDto findByMentorName(String name);

    StudyTripDto findByMentorEmail(String email);

    List<StudyTripDto> findAll();

    void delete(String id);
}
