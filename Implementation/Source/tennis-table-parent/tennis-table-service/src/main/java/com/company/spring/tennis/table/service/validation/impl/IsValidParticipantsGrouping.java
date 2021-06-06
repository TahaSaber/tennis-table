package com.company.spring.tennis.table.service.validation.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.spring.tennis.table.dal.repositories.ParticipantRepositroy;
import com.company.spring.tennis.table.service.utilities.RejectionReason;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;
import com.company.spring.tennis.table.service.validation.interfaces.RequestValidation;

@Component("IsValidParticipantsGrouping")
public class IsValidParticipantsGrouping implements RequestValidation {

	@Autowired
	private ParticipantRepositroy participentRepositroy;

	@Override
	public void validate(Object request) throws ValidationException {

		try {

			Integer groupsCount = (Integer) request;

			if (groupsCount <= 0) {

				throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
						ResponseStatusCodesAndMessages.ZERO_GROUP_COUNT));
			}

			long participantsCount = participentRepositroy.count();

			if (participantsCount / groupsCount < 2) {

				throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
						ResponseStatusCodesAndMessages.INVALID_GROUP_COUNT, String.valueOf(participantsCount)));
			}

		} catch (RuntimeException e) {
			throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
					ResponseStatusCodesAndMessages.VALIDATION_ERROR));
		}

	}

}
