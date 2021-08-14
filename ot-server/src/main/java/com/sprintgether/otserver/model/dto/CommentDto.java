package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Comment;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CommentDto {
    private String content;

    public static CommentDto fromEntity(Comment comment){
        if(comment == null){
            return null;
        }

        return CommentDto.builder()
                .content(comment.getContent())
                .build();
    }

    public static Comment toEntity(CommentDto commentDto){
        if(commentDto == null){
            return null;
        }
        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        return comment;
    }
}
