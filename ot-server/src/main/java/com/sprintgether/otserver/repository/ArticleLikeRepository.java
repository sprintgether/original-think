package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.ArticleLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArticleLikeRepository extends JpaRepository<ArticleLike, String> {
}

