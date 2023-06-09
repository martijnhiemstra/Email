package eu.codefounders.training.email;

import java.io.File;
import java.nio.file.Paths;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendSimpleMessage(String from, String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(text);
		
		emailSender.send(message);
	}

	public void sendMessageWithAttachment(String from, String to, String subject, String text) throws MessagingException {
		MimeMessage message = emailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);

		File attachmentFile = Paths.get("target/classes/sample.pdf").toFile();
		helper.addAttachment("Sample 1.pdf", attachmentFile);

		emailSender.send(message);
	}

}
