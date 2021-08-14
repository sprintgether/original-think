package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.SupervisorDto;
import com.sprintgether.otserver.repository.SupervisorRepository;
import com.sprintgether.otserver.service.core.SupervisorService;
import com.sprintgether.otserver.validator.SupervisorValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SupervisorServiceImpl implements SupervisorService {
    SupervisorRepository supervisorRepository;

    @Autowired
    public SupervisorServiceImpl(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public SupervisorDto save(SupervisorDto dto) {
        List<String> errors = SupervisorValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Supervisor is not VALID", dto);
            throw new InvalidEntityException("l'entité Supervisor n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return SupervisorDto.fromEntity(
                supervisorRepository.save(
                        SupervisorDto.toEntity(dto)
                )
        );
    }

    @Override
    public SupervisorDto findById(String id) {
        return null;
    }

    @Override
    public SupervisorDto findByEmail(String email) {
        return null;
    }

    @Override
    public List<SupervisorDto> findAll() {
        return supervisorRepository.findAll().stream()
                .map(SupervisorDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("SUPERVISOR ID is null");
            return;
        }
        supervisorRepository.deleteById(id);
    }
}
