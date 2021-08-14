package com.sprintgether.otserver.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprintgether.otserver.model.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    private String email;

    @JsonIgnore
    private boolean isEmailVerified;

    public static UserDto fromEntity(User user){
        if (user == null){
            return null;
        }

        return UserDto.builder()
                .email(user.getEmail())
                .isEmailVerified(user.isEmailVerified())
                .build();
    }

    public static User toEntity(UserDto userDto){
        if (userDto == null){
            return null;
        }

        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setEmailVerified(userDto.isEmailVerified);
        return user;
    }
}
