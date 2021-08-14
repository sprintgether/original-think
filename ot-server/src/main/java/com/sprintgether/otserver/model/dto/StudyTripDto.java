package com.sprintgether.otserver.model.dto;

import com.sprintgether.otserver.model.entity.StudyTrip;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class StudyTripDto {
    private String locality;
    private Instant startedAt;
    private String duration;
    private String mentorName;
    private String mentorEmail;

    public static StudyTripDto fromEntity(StudyTrip studyTrip){
        if(studyTrip == null){
            return null;
        }

        return StudyTripDto.builder()
                .locality(studyTrip.getLocality())
                .startedAt(studyTrip.getStartedAt())
                .duration(studyTrip.getDuration())
                .mentorName(studyTrip.getMentorName())
                .mentorEmail(studyTrip.getMentorEmail())
                .build();
    }

    public static StudyTrip toEntity(StudyTripDto studyTripDto){
        if(studyTripDto == null){
            return null;
        }

        StudyTrip studyTrip = new StudyTrip();
        studyTrip.setLocality(studyTripDto.getLocality());
        studyTrip.setStartedAt(studyTripDto.getStartedAt());
        studyTrip.setDuration(studyTripDto.getDuration());
        studyTrip.setMentorName(studyTripDto.getMentorName());
        studyTrip.setMentorEmail(studyTripDto.getMentorEmail());

        return studyTrip;
    }
}
