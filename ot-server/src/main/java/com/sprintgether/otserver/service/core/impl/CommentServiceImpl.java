package com.sprintgether.otserver.service.core.impl;

import com.sprintgether.otserver.exception.EnumErrorCode;
import com.sprintgether.otserver.exception.InvalidEntityException;
import com.sprintgether.otserver.model.dto.CommentDto;
import com.sprintgether.otserver.repository.CommentRepository;
import com.sprintgether.otserver.service.core.CommentService;
import com.sprintgether.otserver.validator.CommentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto save(CommentDto commentDto) {
        List<String> errors = CommentValidator.validate(commentDto);
        if(!errors.isEmpty()){
            log.error("Author is not VALID", commentDto);
            throw new InvalidEntityException("l'entité Comment n'est pas valid", EnumErrorCode.ERROR_DB_ITEM_NOTFOUND, errors);
        }
        return CommentDto.fromEntity(
                commentRepository.save(
                        CommentDto.toEntity(commentDto)
                )
        );
    }

    @Override
    public CommentDto findById(String id) {
        return null;
    }

    @Override
    public CommentDto findByContent(String content) {
        return null;
    }

    @Override
    public List<CommentDto> findAll() {
        return commentRepository.findAll().stream()
                .map(CommentDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        if(id == null){
            log.error("COMMENT ID is null");
            return;
        }
        commentRepository.deleteById(id);
    }
}
