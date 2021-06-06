package com.company.spring.tennis.table.service.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.spring.tennis.table.dal.repositories.ParticipantRepositroy;
import com.company.spring.tennis.table.service.utilities.RejectionReason;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;
import com.company.spring.tennis.table.service.validation.interfaces.RequestValidation;

@Component("IsEmailExist")
public class IsEmailExist implements RequestValidation {

	@Autowired
	private ParticipantRepositroy participentRepositroy;

	@Override
	public void validate(Object request) throws ValidationException {

		try {

			String email = (String) request;

			if (!participentRepositroy.existsByEmail(email)) {

				throw new ValidationException(
						new RejectionReason(ResponseStatusCodesAndMessages.DUBLICATE_RESOURCE_CODE,
								ResponseStatusCodesAndMessages.EMAIL_NOT_EXIST, email));
			}

		} catch (RuntimeException e) {
			throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
					ResponseStatusCodesAndMessages.VALIDATION_ERROR));
		}

	}

}
