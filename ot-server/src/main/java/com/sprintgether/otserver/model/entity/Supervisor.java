package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ot_supervisor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Supervisor extends MainEntity {

    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String institution;

    @ManyToOne(targetEntity = Thesis.class)
    private Thesis thesis;

}
