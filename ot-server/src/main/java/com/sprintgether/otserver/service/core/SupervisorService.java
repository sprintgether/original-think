package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.SupervisorDto;
import java.util.List;

public interface SupervisorService {
    SupervisorDto save(SupervisorDto supervisorDto);

    SupervisorDto findById(String id);

    SupervisorDto findByEmail(String email);

    List<SupervisorDto> findAll();

    void delete(String id);
}
