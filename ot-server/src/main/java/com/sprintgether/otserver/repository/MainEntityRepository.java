package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.MainEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainEntityRepository extends JpaRepository<MainEntity, String> {
}
