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
@Table(name = "ot_author")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author extends MainEntity {

    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String institution;

    @ManyToOne(targetEntity = Think.class)
    private Think think;

    @ManyToOne(targetEntity = Talk.class)
    private Talk talk;
}
