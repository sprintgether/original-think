package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Article;
import com.sprintgether.otserver.model.entity.StudyTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyTripRepository extends JpaRepository<StudyTrip, String> {
    Optional<StudyTrip> findStudyTripByLocality(String locality);
    Optional<StudyTrip> findStudyTripByMentorName(String mentorName);
    Optional<StudyTrip> findStudyTripByMentorEmail(String mentorEmail);
}
