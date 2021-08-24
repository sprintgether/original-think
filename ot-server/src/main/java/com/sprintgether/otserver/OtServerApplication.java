package com.sprintgether.otserver;

import com.sprintgether.otserver.exception.MailSendException;
import com.sprintgether.otserver.model.entity.Mail;
import com.sprintgether.otserver.model.entity.Role;
import com.sprintgether.otserver.model.entity.User;
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
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import java.io.IOException;


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
		// Initialiser les rôles dans le système
		roleService.initRoles();

		/*System.out.println("Sending Email...");

		sendEmail();

		System.out.println("Done");*/
	}

	void sendEmail() {
		try{
			System.out.println("sending .....");

			Mail mail = Mail.builder()
					.subject("Activation de l'adresse mail")
					.to("pe8977461@gmail.com")
					.content("Veuillez cliquer sur ce pour vérifier votre adresse email...............")
					.build();
			mailService.send(mail);

			System.out.println("mail envoyé ......");
		}catch (IOException e){
			System.out.println("mail non envoyé ......");
			LOGGER.error(String.valueOf(e));
			throw new MailSendException("pe8977461@gmail.com", "Email verification");
		}


		/*SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo("pe8977461@gmail.com");

		msg.setSubject("Testing from Spring Boot");
		msg.setText("Hello World \n Spring Boot Email");

		javaMailSender.send(msg);
		System.out.println("mail envoyé ......");*/
	}
}
