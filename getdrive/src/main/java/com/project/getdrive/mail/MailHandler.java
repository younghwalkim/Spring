package com.project.getdrive.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;



@Service
public class MailHandler {
	
	@Autowired
	private JavaMailSender mailSender;
	//private MimeMassageHelper msgHelper;
	
	@Async
	public void sendEmail(String from, String to, String title, String contents) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(title);
		message.setText(contents);
		
		mailSender.send(message);
	}

	
	
}
