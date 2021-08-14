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
@Table(name = "ot_keyword")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class KeyWord extends MainEntity {

    private String value;

    @ManyToOne(targetEntity = Article.class)
    private Article article;
}
