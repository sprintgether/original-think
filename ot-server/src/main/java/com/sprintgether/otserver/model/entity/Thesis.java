package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprintgether.otserver.model.enums.EnumThesisType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ot_thesis")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Thesis extends Article {

    private String documentLink;
    private String grade;

    @Enumerated(EnumType.STRING)
    private EnumThesisType thesisType;

    @OneToOne(targetEntity = Author.class)
    private Author author;

    @JsonIgnore
    @OneToMany(mappedBy = "thesis")
    private List<Supervisor> supervisors;
}
