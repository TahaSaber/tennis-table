package com.company.spring.tennis.table.service.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.spring.tennis.table.dal.repositories.ParticipantRepositroy;
import com.company.spring.tennis.table.service.types.ParticipantDto;
import com.company.spring.tennis.table.service.utilities.Constants;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.utilities.RejectionReason;
import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;
import com.company.spring.tennis.table.service.validation.interfaces.RequestValidation;

@Component("IsValidAddingParticipantRequest")
public class IsValidAddingParticipantRequest implements RequestValidation {

	@Autowired
	private ParticipantRepositroy participentRepositroy;

	@Override
	public void validate(Object request) throws ValidationException {

		try {

			ParticipantDto participentDto = (ParticipantDto) request;

			if (participentRepositroy.existsByEmail(participentDto.getEmail())) {

				throw new ValidationException(
						new RejectionReason(ResponseStatusCodesAndMessages.DUBLICATE_RESOURCE_CODE,
								ResponseStatusCodesAndMessages.DUBLICATE_EMAIL, participentDto.getEmail()));
			}

			if (participentRepositroy.existsByUserName(participentDto.getUserName())) {

				throw new ValidationException(
						new RejectionReason(ResponseStatusCodesAndMessages.DUBLICATE_RESOURCE_CODE,
								ResponseStatusCodesAndMessages.DUBLICATE_USERNAME, participentDto.getUserName()));
			}

			if (participentRepositroy.count() >= Constants.MAX_PARTICIPENTS_COUNT) {

				throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
						ResponseStatusCodesAndMessages.PARTICIPENTS_COUNT_VIOLATION));
			}

		} catch (RuntimeException e) {
			throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
					ResponseStatusCodesAndMessages.VALIDATION_ERROR));
		}

	}

}
