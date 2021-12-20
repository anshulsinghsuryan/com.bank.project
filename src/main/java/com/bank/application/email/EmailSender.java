package com.bank.application.email;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bank.application.enums.FileConstants;
import com.bank.application.utility.CommonUtils;

@Service
public class EmailSender {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
	
	@Autowired
	private SessionProvider sessionProvider;

	@Value("${spring.mail.username}")
	private String email;

	public void sendEmail(String receiver, String subject, String text) {

		Session session = sessionProvider.getSession();
		
		Message message = new MimeMessage(session);
		try {
			InternetAddress internetAddressFrom = new InternetAddress(email);
			message.setFrom(internetAddressFrom);

			InternetAddress internetAddressTo = new InternetAddress(receiver);
			message.addRecipient(Message.RecipientType.TO, internetAddressTo);

			message.setSubject(subject);
			message.setContent(text, FileConstants.CONTENT_TYPE.getStrValue());

			Transport.send(message);

		} catch (Exception e) {
			LOGGER.error("ERROR : error in sending email !! {}",CommonUtils.getLogMessage(e));
		}
	}
}
