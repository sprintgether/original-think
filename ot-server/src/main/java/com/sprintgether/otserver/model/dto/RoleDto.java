package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Roles;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {
    private String roleName;
    private String description;

    public static RoleDto fromEntity(Roles role){
        if(role == null){
            return null;
        }
        return RoleDto.builder()
                .roleName(role.getRoleName())
                .description(role.getDescription())
                .build();
    }

    public static Roles toEntity(RoleDto roleDto){
        if(roleDto == null){
            return null;
        }

        Roles role = new Roles();
        role.setRoleName(roleDto.getRoleName());
        role.setDescription(roleDto.getDescription());
        return role;
    }
}
