package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.StudyTripDto;
import com.sprintgether.otserver.repository.StudyTripRepository;
import com.sprintgether.otserver.service.core.StudyTripService;
import com.sprintgether.otserver.validator.StudyTripValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudyTripServiceImpl implements StudyTripService {
    StudyTripRepository studyTripRepository;

    @Autowired
    public StudyTripServiceImpl(StudyTripRepository studyTripRepository) {
        this.studyTripRepository = studyTripRepository;
    }

    @Override
    public StudyTripDto save(StudyTripDto dto) {
        List<String> errors = StudyTripValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("StudyTrip is not VALID", dto);
            throw new InvalidEntityException("l'entité StudyTrip n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return StudyTripDto.fromEntity(
                studyTripRepository.save(
                        StudyTripDto.toEntity(dto)
                )
        );
    }

    @Override
    public StudyTripDto findById(String id) {
        return null;
    }

    @Override
    public StudyTripDto findByLocality(String locality) {
        return null;
    }

    @Override
    public StudyTripDto findByMentorName(String name) {
        return null;
    }

    @Override
    public StudyTripDto findByMentorEmail(String email) {
        return null;
    }

    @Override
    public List<StudyTripDto> findAll() {
        return studyTripRepository.findAll().stream()
                .map(StudyTripDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("StudyStrip ID is null");
            return;
        }
        studyTripRepository.deleteById(id);
    }
}
