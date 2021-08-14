package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ot_article_like")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleLike extends MainEntity {

    @ManyToOne(targetEntity = Article.class)
    private Article article;

    @ManyToOne(targetEntity = User.class)
    private User user;

}
