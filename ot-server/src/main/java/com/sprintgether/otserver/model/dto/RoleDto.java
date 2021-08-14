package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private String roleName;
    private String description;

    public static RoleDto fromEntity(Role role){
        if(role == null){
            return null;
        }
        return RoleDto.builder()
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    public static Role toEntity(RoleDto roleDto){
        if(roleDto == null){
            return null;
        }

        Role role = new Role();
        role.setRoleName(roleDto.getRoleName());
        role.setDescription(roleDto.getDescription());
        return role;
    }
}
