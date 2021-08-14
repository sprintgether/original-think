package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.enums.EnumTokenStatus;
import com.sprintgether.otserver.model.enums.EnumTokenType;

import java.util.Optional;

public interface TokenService {
    Token save(Token token);
    Optional<Token> findByValue(String token);
    Optional<Token> findByUser(User user);
    void verifyExpiration(Token token);
    Token updateExistingTokenWithStatusAndExpiry(Token existingToken);
    //Token createTokenByType(User user, EnumTokenType tokenType);
    Token createTokenByType(User user, EnumTokenType tokenType, EnumTokenStatus enumTokenStatus, String tokenValue, Long expiryDuration);
    Optional<Token> findByToken(String token);
}
