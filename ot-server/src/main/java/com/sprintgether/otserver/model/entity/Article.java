package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprintgether.otserver.model.enums.EnumVisibility;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Article extends MainEntity {

     protected String theme;
     protected String domain;
     protected String description;
     protected String abstracts;
     protected Boolean isPublished;
     protected Instant publishedAt;

     @OneToOne(targetEntity = File.class)
     protected File video;

     @OneToOne(targetEntity = File.class)
     protected File document;

     @OneToOne(targetEntity = File.class)
     protected File cover;

     @Enumerated(EnumType.STRING)
     protected EnumVisibility visibility;

     @JsonIgnore
     @OneToMany(mappedBy = "article")
     protected List<Comment> comments;

     @JsonIgnore
     @OneToMany(mappedBy = "article")
     protected List<KeyWord> keywords;

     protected long nbLike;
     protected long nbView;
}
