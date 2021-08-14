package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.exception.OtDBItemNotFoundException;
import com.sprintgether.otserver.model.dto.AuthorDto;
import com.sprintgether.otserver.model.entity.Author;
import com.sprintgether.otserver.repository.AuthorRepository;
import com.sprintgether.otserver.service.core.AuthorService;
import com.sprintgether.otserver.validator.AuthorValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AuthorServiceImpl implements AuthorService {

    AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) throws InvalidEntityException{
        List<String> errors = AuthorValidator.validate(authorDto);
        if(!errors.isEmpty()){
            log.error("Author is not VALID", authorDto);
            throw new InvalidEntityException("l'entité author n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return AuthorDto.fromEntity(
                authorRepository.save(
                        AuthorDto.toEntity(authorDto)
                )
        );
    }

    @Override
    public Author findById(String id) throws OtDBItemNotFoundException {
        return authorRepository.findById(id)
                .orElseThrow(() ->
                new OtDBItemNotFoundException(EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, Author.class.getSimpleName(), "id", id));
    }

    @Override
    public void delete(String id) throws OtDBItemNotFoundException{
        Author author = findById(id);
        authorRepository.delete(author);
    }

    @Override
    public AuthorDto findByFirstName(String firstName) {
        if(!StringUtils.hasLength(firstName)){
            log.error("AUTHOR firstName is null");
        }

        Optional<Author> article = authorRepository.findAuthorByFirstName(firstName);
        return Optional.of(AuthorDto.fromEntity(article.get())).orElseThrow(()
                -> new InvalidEntityException("Aucun auteur avec le firstname "  + firstName + "n'a été trouvé", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND) );
    }

    @Override
    public AuthorDto findByEmail(String email) {
        if(!StringUtils.hasLength(email)){
            log.error("AUTHOR email is null");
        }

        Optional<Author> article = authorRepository.findAuthorByFirstName(email);
        return Optional.of(AuthorDto.fromEntity(article.get())).orElseThrow(()
                -> new InvalidEntityException("Aucun auteur avec l'email "  + email + "n'a été trouvé", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND) );
    }

    @Override
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream()
                .map(AuthorDto::fromEntity)
                .collect(Collectors.toList());
    }
}
