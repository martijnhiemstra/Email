package eu.codefounders.training.email;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import de.svenjacobs.loremipsum.LoremIpsum;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;

	@GetMapping("/sendemail")
	public void sendEmail() {
		LoremIpsum loremIpsum = new LoremIpsum();

		this.emailService.sendSimpleMessage("noreply@demo.nl", "to@demo.nl", "Demo bericht", loremIpsum.getParagraphs(2));
	}

	@GetMapping("/sendemailwithattachment")
	public void sendEmailWithAttachment() {
		LoremIpsum loremIpsum = new LoremIpsum();

		try {
			this.emailService.sendMessageWithAttachment("noreply@codefounders.nl", "to@codefounders.nl", "Demo bericht met attachment", loremIpsum.getParagraphs(5));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
