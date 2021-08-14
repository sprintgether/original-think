package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.ArticleView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleViewRepository  extends JpaRepository<ArticleView, String> {
}
