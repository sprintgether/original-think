package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.TalkDto;
import com.sprintgether.otserver.repository.TalkRepository;
import com.sprintgether.otserver.service.core.TalkService;
import com.sprintgether.otserver.validator.TalkValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TalkServiceImpl implements TalkService {
    TalkRepository talkRepository;

    public TalkServiceImpl(TalkRepository talkRepository) {
        this.talkRepository = talkRepository;
    }

    @Override
    public TalkDto save(TalkDto dto) {
        List<String> errors = TalkValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Talk is not VALID", dto);
            throw new InvalidEntityException("l'entité Talk n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return TalkDto.fromEntity(
                talkRepository.save(
                        TalkDto.toEntity(dto)
                )
        );
    }

    @Override
    public TalkDto findById(String id) {
        return null;
    }

    @Override
    public TalkDto findByStudyLevel(String studyLevel) {
        return null;
    }

    @Override
    public List<TalkDto> findAll() {
        return talkRepository.findAll().stream()
                .map(TalkDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("TALK ID is null");
            return;
        }
        talkRepository.deleteById(id);
    }

}
