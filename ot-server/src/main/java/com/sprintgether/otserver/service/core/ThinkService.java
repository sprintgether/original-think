package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.ThinkDto;
import com.sprintgether.otserver.model.entity.Think;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ThinkService {

    ThinkDto save(Think think);

    ThinkDto save(ThinkDto thinkDto);

    ThinkDto findById(String id);

    ThinkDto findByJournal(String journal);

    List<ThinkDto> findAll();

    void delete(String id);

    ThinkDto createThink(String creatorId, MultipartFile document, ThinkDto thinkDto) throws IOException;
}
