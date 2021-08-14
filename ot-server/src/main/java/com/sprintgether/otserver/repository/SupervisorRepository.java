package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Article;
import com.sprintgether.otserver.model.entity.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupervisorRepository extends JpaRepository<Supervisor, String> {
    Optional<Supervisor> findSupervisorByEmail(String email);
}
