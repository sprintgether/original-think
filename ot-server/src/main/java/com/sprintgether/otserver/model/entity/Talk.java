package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ot_talk")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Talk extends Article{

    private String studyLevel;

    @JsonIgnore
    @OneToMany(mappedBy = "talk")
    private List<Author> authors;
}
