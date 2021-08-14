package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Author;
import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.enums.EnumTokenStatus;
import com.sprintgether.otserver.model.enums.EnumTokenType;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.time.Instant;

@Builder
@Data
public class TokenDto {
    private String token;

    @Enumerated(EnumType.STRING)
    private EnumTokenType tokenType;

    @Enumerated(EnumType.STRING)
    private EnumTokenStatus tokenStatus;

    private Instant expiryDate;

    private User user;


    public static TokenDto fromEntity(Token token){
        if(token == null){
            return null;
        }

        return TokenDto.builder()
                .token(token.getValue())
                .tokenType(token.getTokenType())
                .tokenStatus(token.getTokenStatus())
                .expiryDate(token.getExpiryDate())
                .user(token.getUser())
                .build();
    }

    public static Token toEntity(TokenDto tokenDto){
        if(tokenDto == null){
            return null;
        }

        Token token = new Token();
        token.setValue(tokenDto.getToken());
        token.setTokenType(tokenDto.getTokenType());
        token.setTokenStatus(tokenDto.getTokenStatus());
        token.setExpiryDate(tokenDto.getExpiryDate());
        token.setUser(tokenDto.getUser());

        return token;
    }
}
