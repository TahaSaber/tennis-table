package com.company.spring.tennis.table.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.interfaces.EmailService;
import com.company.spring.tennis.table.service.utilities.RejectionReason;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;

@Component
public class EmailServiceImpl implements EmailService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender emailSender;

	@Override
	public void sendSimpleMessage(String to, String subject, String text) throws ServiceException {
		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("tmail3864@gmail.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);
		} catch (RuntimeException e) {
			log.error("Mail Error {}", e.getMessage());
			RejectionReason reason = new RejectionReason();
			reason.setCode(ResponseStatusCodesAndMessages.FAILED_CODE);
			reason.setReason(ResponseStatusCodesAndMessages.FAILED_MAIL);
			throw new ServiceException(reason);
		}

	}

}
