package com.company.spring.tennis.table.service.interfaces;

import com.company.spring.tennis.table.service.exceptions.ServiceException;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text) throws ServiceException;
}
