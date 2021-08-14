package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Builder
@Data
public class ArticleDto {
    protected String theme;
    protected String domain;
    protected String description;
    protected String abstracts;
    protected Boolean isPublished;
    protected Instant publishedAt;

    public static ArticleDto fromEntity(Article article){
       return null;
    }

    public static Article toEntity(ArticleDto articleDto){
        return null;
    }
}
