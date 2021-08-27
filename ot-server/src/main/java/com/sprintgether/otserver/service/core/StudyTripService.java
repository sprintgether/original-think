package com.sprintgether.otserver.service.core;


import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.ArticleDto;
import com.sprintgether.otserver.model.dto.StudyTripDto;
import com.sprintgether.otserver.model.entity.StudyTrip;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StudyTripService {
    StudyTripDto save(StudyTrip studyTrip);

    StudyTripDto createStudyTrip(String id, MultipartFile document, MultipartFile cover, StudyTripDto studyTripDto) throws IOException, OtDBItemNotFoundException;
}
