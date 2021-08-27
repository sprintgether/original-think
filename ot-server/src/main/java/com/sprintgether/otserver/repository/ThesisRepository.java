package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ThesisRepository extends JpaRepository<Thesis, String> {

}
