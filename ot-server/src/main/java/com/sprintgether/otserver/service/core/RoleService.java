package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.RoleDto;
import com.sprintgether.otserver.model.entity.Role;

import java.util.List;

public interface RoleService {

    Role save(Role role);

    RoleDto createRole(RoleDto roleDto);

    Role findById(String id) throws OtDBItemNotFoundException;

    RoleDto findByRoleName(String roleName);

    List<RoleDto> findAll();

    void delete(String id);

    /**
     * Intialiser les rôles dans le système
     */
    void initRoles();
}
