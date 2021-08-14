package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.InvalidTokenRequestException;
import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import com.sprintgether.otserver.model.enums.EnumTokenStatus;
import com.sprintgether.otserver.model.enums.EnumTokenType;
import com.sprintgether.otserver.repository.TokenRepository;
import com.sprintgether.otserver.service.core.TokenService;
import com.sprintgether.otserver.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${app.token.email.verification.duration}")
    private String defaultTokenExpiryDuration;

    @Autowired
    private TokenRepository tokenRepository;

    @Override
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findByValue(String token) {
        return tokenRepository.findByValue(token);
    }

    @Override
    public Optional<Token> findByUser(User user) {
        return tokenRepository.findByUser(user);
    }

    @Override
    public void verifyExpiration(Token token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            throw new InvalidTokenRequestException("Email Verification Token", token.getValue(),
                    "Expired token. Please issue a new request");
        }
    }

    @Override
    public Token updateExistingTokenWithStatusAndExpiry(Token existingToken) {
        existingToken.setTokenStatus(EnumTokenStatus.PENDING);
        existingToken.setExpiryDate(Instant.now().plusMillis(Long.parseLong(defaultTokenExpiryDuration)));
        return save(existingToken);
    }

    @Override
    public Token createTokenByType(User user, EnumTokenType tokenType, EnumTokenStatus tokenStatus, String tokenValue, Long expiryDuration) {
        Token token = new Token();
        token.setUser(user);
        //token.setValue(tokenValue);
        token.setValue(TokenUtil.generateRandomUuid());
        token.setTokenType(EnumTokenType.EMAIL_VERIFICATION);
        token.setTokenStatus(EnumTokenStatus.PENDING);
        token.setExpiryDate(Instant.now().plusMillis(expiryDuration));

        return tokenRepository.save(token);
    }

    @Override
    public Optional<Token> findByToken(String token) {
        return tokenRepository.findByValue(token);
    }

}

