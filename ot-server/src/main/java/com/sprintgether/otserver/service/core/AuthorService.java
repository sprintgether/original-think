package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.AuthorDto;
import com.sprintgether.otserver.model.entity.Author;

import java.util.List;

public interface AuthorService {
    AuthorDto save(AuthorDto authorDto);

    Author findById(String id) throws OtDBItemNotFoundException;

    AuthorDto findByFirstName(String firstName) ;

    AuthorDto findByEmail(String email);

    List<AuthorDto> findAll();

    void delete(String id) throws OtDBItemNotFoundException;
}
