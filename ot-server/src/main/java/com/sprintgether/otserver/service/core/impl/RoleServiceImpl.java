package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.entity.Roles;
import com.sprintgether.otserver.repository.RoleRepository;
import com.sprintgether.otserver.service.core.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LogManager.getLogger(RoleServiceImpl.class);

    @Value("${app.roles.admin}")
    private String roleAdmin;

    @Value("${app.roles.user}")
    private String roleUser;

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /*@Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleDto createRole(RoleDto dto) {
        List<String> errors = RoleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Role is not VALID", dto);
            System.out.println("---------------------errors-----------------------");
            System.out.println(errors);
            System.out.println("---------------------errors-----------------------");
            throw new InvalidEntityException("l'entité Role n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return RoleDto.fromEntity(
                roleRepository.save(
                        RoleDto.toEntity(dto)
                )
        );
    }*/

    @Override
    public Roles findById(String id) throws OtDBItemNotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(()-> new OtDBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, Roles.class.getSimpleName(), "roleName", id));
    }

    @Override
    public Roles findByName(String roleName) throws OtDBItemNotFoundException {
        if(roleName == null){
            LOGGER.error("roleName is null");
            return null;
        }

        return roleRepository.findByRoleName(roleName)
                .orElseThrow(()-> new OtDBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, Roles.class.getSimpleName(), "roleName", roleName));
    }


    @Override
    public void initRoles() {
        //Optional<Role> roleAdminOptional = roleRepository.findByRoleName(roleAdmin);
        Optional<Roles> roleAdminOptional = roleRepository.findById(roleAdmin);
        if(!roleAdminOptional.isPresent())
            roleRepository.save(new Roles(roleAdmin, "Administrateur du système"));

        Optional<Roles> roleUserOptional = roleRepository.findById(roleUser);
        //Optional<Role> roleUserOptional = roleRepository.findByRoleName(roleUser);
        if(!roleUserOptional.isPresent())
            roleRepository.save(new Roles(roleUser, "Utilisateur simple"));
    }
}
