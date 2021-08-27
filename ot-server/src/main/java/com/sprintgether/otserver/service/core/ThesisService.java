package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.ThesisDto;
import com.sprintgether.otserver.model.entity.Thesis;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface ThesisService {
    ThesisDto createThesis(String creatorId, MultipartFile document, MultipartFile cover, ThesisDto thesisDto) throws IOException, OtDBItemNotFoundException;
    ThesisDto save(Thesis thesis);
}
