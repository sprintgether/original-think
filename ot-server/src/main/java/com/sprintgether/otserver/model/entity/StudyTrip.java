package com.sprintgether.otserver.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "ot_study_trip")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudyTrip extends Article {

    private String locality;
    private Instant startedAt;
    private String duration;
    private String mentorName;
    private String mentorEmail;
}
