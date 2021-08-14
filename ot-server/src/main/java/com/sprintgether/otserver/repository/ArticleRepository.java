package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, String> {
    /*Optional<Article> findArticleByTheme(String theme);
    Optional<Article> findArticleByDomain(String domain);
    Optional<Article> findArticleByIsPublished(boolean isPublished);*/
}
