package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.TalkDto;
import com.sprintgether.otserver.model.entity.Talk;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TalkService {
    TalkDto save(Talk talk);

    Object createTalk(String creatorId, MultipartFile document, MultipartFile cover, TalkDto talkDto) throws OtDBItemNotFoundException;
}
