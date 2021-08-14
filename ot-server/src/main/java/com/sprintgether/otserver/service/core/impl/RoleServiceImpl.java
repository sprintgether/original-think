package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.exception.OtDBItemInvalidException;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.RoleDto;
import com.sprintgether.otserver.model.entity.Role;
import com.sprintgether.otserver.repository.RoleRepository;
import com.sprintgether.otserver.service.core.RoleService;
import com.sprintgether.otserver.validator.RoleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    @Value("${app.roles.admin}")
    private String roleAdmin;

    @Value("${app.roles.user}")
    private String roleUser;

    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleDto createRole(RoleDto dto) {
        List<String> errors = RoleValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Role is not VALID", dto);
            throw new InvalidEntityException("l'entité Role n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return RoleDto.fromEntity(
                roleRepository.save(
                        RoleDto.toEntity(dto)
                )
        );
    }

    @Override
    public Role findById(String id) throws OtDBItemNotFoundException {
        return roleRepository.findById(id)
                .orElseThrow(()-> new OtDBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, Role.class.getSimpleName(), "roleName", id));
    }

    @Override
    public RoleDto findByRoleName(String roleName) {
        return null;
    }

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream()
                .map(RoleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("ROLE ID is null");
            return;
        }
        roleRepository.deleteById(id);
    }

    @Override
    public void initRoles() {
        Optional<Role> roleAdminOptional = roleRepository.findById(roleAdmin);
        if(!roleAdminOptional.isPresent())
            roleRepository.save(new Role(roleAdmin, "Administrateur du système"));

        Optional<Role> roleUserOptional = roleRepository.findById(roleUser);
        if(!roleUserOptional.isPresent())
            roleRepository.save(new Role(roleUser, "Utilisateur simple"));
    }
}
