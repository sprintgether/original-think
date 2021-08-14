package com.sprintgether.otserver.service.core;

import com.sprintgether.otserver.model.dto.CommentDto;
import java.util.List;

public interface CommentService {
    CommentDto save(CommentDto commentDto);

    CommentDto findById(String id);

    CommentDto findByContent(String content);

    List<CommentDto> findAll();

    void delete(String id);
}
