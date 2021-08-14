package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EntityNotFoundException;
import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.KeyWordDto;
import com.sprintgether.otserver.model.entity.KeyWord;
import com.sprintgether.otserver.repository.KeyWordRepository;
import com.sprintgether.otserver.service.core.KeyWordService;
import com.sprintgether.otserver.validator.KeyWordValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class KeyWordServiceImpl implements KeyWordService {
    KeyWordRepository keyWordRepository;

    public KeyWordServiceImpl(KeyWordRepository keyWordRepository) {
        this.keyWordRepository = keyWordRepository;
    }

    @Override
    public KeyWordDto save(KeyWordDto dto) {
        List<String> errors = KeyWordValidator.validate(dto);
        if(!errors.isEmpty()){
            log.error("KeyWord is not VALID", dto);
            throw new InvalidEntityException("l'entité KeyWord n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return KeyWordDto.fromEntity(
                keyWordRepository.save(
                        KeyWordDto.toEntity(dto)
                )
        );
    }

    @Override
    public KeyWordDto findById(String id) {
        if(id == null){
            log.error("KeyWord ID is NULL");
            return null;
        }

        Optional<KeyWord> keyWord = keyWordRepository.findById(id);

        return Optional.of(KeyWordDto.fromEntity(keyWord.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun KeyWord avec l' ID "  + id + " n'a été trouvé ", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND)
        );
    }

    @Override
    public KeyWordDto findByValue(String value) {
        if(!StringUtils.hasLength(value)){
            log.error("KeyWord VALUE is NULL");
            return null;
        }

        Optional<KeyWord> keyWord = keyWordRepository.findKeyWordByValue(value);

        return Optional.of(KeyWordDto.fromEntity(keyWord.get())).orElseThrow(() ->
                new EntityNotFoundException("Aucun KeyWord avec la valeur "  + value + " n'a été trouvé ", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND)
        );
    }

    @Override
    public List<KeyWordDto> findAll() {
        return keyWordRepository.findAll().stream()
                .map(KeyWordDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("KEYWAORD ID is null");
            return;
        }
        keyWordRepository.deleteById(id);
    }
}
