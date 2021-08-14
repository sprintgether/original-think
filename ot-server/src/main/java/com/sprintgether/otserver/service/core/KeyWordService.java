package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.KeyWordDto;
import java.util.List;

public interface KeyWordService {
    KeyWordDto save(KeyWordDto keyWordDto);

    KeyWordDto findById(String id);

    KeyWordDto findByValue(String value);

    List<KeyWordDto> findAll();

    void delete(String id);
}
