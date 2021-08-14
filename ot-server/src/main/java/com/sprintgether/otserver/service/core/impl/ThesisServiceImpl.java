package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.ThesisDto;
import com.sprintgether.otserver.repository.ThesisRepository;
import com.sprintgether.otserver.service.core.ThesisService;
import com.sprintgether.otserver.validator.ThesisValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ThesisServiceImpl implements ThesisService {
    ThesisRepository thesisRepository;

    public ThesisServiceImpl(ThesisRepository thesisRepository) {
        this.thesisRepository = thesisRepository;
    }

    @Override
    public ThesisDto save(ThesisDto dto) {
        List<String> errors = ThesisValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Thesis is not VALID", dto);
            throw new InvalidEntityException("l'entité Thesis n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return ThesisDto.fromEntity(
                thesisRepository.save(
                        ThesisDto.toEntity(dto)
                )
        );
    }

    @Override
    public ThesisDto findById(String id) {
        return null;
    }

    @Override
    public ThesisDto findByGrade(String grade) {
        return null;
    }

    @Override
    public List<ThesisDto> findAll() {
        return thesisRepository.findAll().stream()
                .map(ThesisDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("THESIS ID is null");
            return;
        }
        thesisRepository.deleteById(id);
    }

}
