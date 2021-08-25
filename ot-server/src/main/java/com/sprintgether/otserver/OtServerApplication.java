package com.sprintgether.otserver;

import com.sprintgether.otserver.exception.MailSendException;
import com.sprintgether.otserver.model.entity.Mail;

import com.sprintgether.otserver.service.MailService;
import com.sprintgether.otserver.service.core.RoleService;
import com.sprintgether.otserver.service.core.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class OtServerApplication implements CommandLineRunner {
	private static final Logger LOGGER = LogManager.getLogger(OtServerApplication.class);

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	@Qualifier("mailServiceImplUseSmtp")
	private MailService mailService;

	public static void main(String[] args) {
		SpringApplication.run(OtServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("Initialiser les rôles dans le système");
		roleService.initRoles();
	}
}
