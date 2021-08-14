package com.sprintgether.otserver.repository;

import com.sprintgether.otserver.model.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MailRepository extends JpaRepository<Mail, String> {
}
