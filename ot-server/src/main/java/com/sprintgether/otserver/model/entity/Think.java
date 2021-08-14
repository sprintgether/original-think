package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprintgether.otserver.model.enums.EnumJournalType;
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
@Table(name = "ot_think")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Think extends Article{

    private String documentLink;
    private String journal;

    @Enumerated(EnumType.STRING)
    private EnumJournalType journalType;

    @JsonIgnore
    @OneToMany(mappedBy = "think")
    private List<Author> authors;
}
