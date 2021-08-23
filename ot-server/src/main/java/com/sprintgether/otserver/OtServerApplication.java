package com.sprintgether.otserver;

import com.sprintgether.otserver.service.MailService;
import com.sprintgether.otserver.service.core.RoleService;
import com.sprintgether.otserver.service.core.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

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
	private MailService mailService;

	public static void main(String[] args) {
		SpringApplication.run(OtServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("Initialiser les rôles dans le système");
		roleService.initRoles();

		LOGGER.debug("Effectuer un envoi test de mail par SMTP");
		mailService.deliverWithSmtp();
		LOGGER.debug("Mail envoyé avec succès!");
	}

}
