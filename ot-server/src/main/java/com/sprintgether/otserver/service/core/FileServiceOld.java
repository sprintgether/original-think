package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.ArticleDto;
import com.sprintgether.otserver.model.dto.CommentDto;
import com.sprintgether.otserver.model.dto.FileDto;

import java.util.List;

public interface FileServiceOld {
    FileDto save(FileDto fileDto);

    FileDto findById(String id);

    FileDto findByName(String name);

    List<FileDto> findAll();

    void delete(String id);
}
