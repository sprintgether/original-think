package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.entity.Role;

public interface RoleService {

    /*Role save(Role role);

    RoleDto createRole(RoleDto roleDto);*/

    Role findById(String id) throws OtDBItemNotFoundException;

    Role findByName(String roleName) throws OtDBItemNotFoundException;


    /**
     * Intialiser les rôles dans le système
     */
    void initRoles();
}
