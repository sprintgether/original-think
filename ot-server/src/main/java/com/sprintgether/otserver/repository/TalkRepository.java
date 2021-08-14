package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Article;
import com.sprintgether.otserver.model.entity.Talk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TalkRepository extends JpaRepository<Talk, String> {
    Optional<Talk> findTalkByStudyLevel(String studyLevel);
}
