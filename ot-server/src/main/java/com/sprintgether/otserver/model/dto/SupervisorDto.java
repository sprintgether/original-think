package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Supervisor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class SupervisorDto {
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String institution;

    public static SupervisorDto fromEntity(Supervisor supervisor){
        if(supervisor == null){
            return null;
        }

        return SupervisorDto.builder()
                .firstName(supervisor.getFirstName())
                .lastName(supervisor.getLastName())
                .title(supervisor.getTitle())
                .email(supervisor.getEmail())
                .institution(supervisor.getInstitution())
                .build();
    }

    public static Supervisor toEntity(SupervisorDto supervisorDto){
        if(supervisorDto == null){
            return null;
        }

        Supervisor supervisor = new Supervisor();
        supervisor.setFirstName(supervisor.getFirstName());
        supervisor.setLastName(supervisor.getLastName());
        supervisor.setTitle(supervisor.getTitle());
        supervisor.setEmail(supervisor.getEmail());
        supervisor.setInstitution(supervisor.getInstitution());

        return supervisor;
    }
}
