package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.ThesisDto;
import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.entity.Think;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ThinkService {

    ThinkDto save(Think think);

    ThinkDto createThink(String creatorId, MultipartFile document, MultipartFile cover, ThinkDto thinkDto) throws IOException, OtDBItemNotFoundException;
}
