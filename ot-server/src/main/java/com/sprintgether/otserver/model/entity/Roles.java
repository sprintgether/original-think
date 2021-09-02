package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ot_role")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Roles implements Serializable {   //extends MainEntity {     //

    @Id
    private String roleName;
    private String description;
}
