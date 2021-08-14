package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Token;
import com.sprintgether.otserver.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, String> {
    Optional<Token> findByValue(String token);

    Optional<Token> findByUser(User user);

    /*Optional<Token> findByToken(String token);*/
}
