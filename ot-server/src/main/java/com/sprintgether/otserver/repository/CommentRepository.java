package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Article;
import com.sprintgether.otserver.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.AbstractDocument;
import java.util.Optional;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, String> {
    Optional<Comment> findCommentByContent(String content);
}
