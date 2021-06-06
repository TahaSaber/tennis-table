package com.company.spring.tennis.table.service.validation.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.spring.tennis.table.dal.repositories.ParticipantRepositroy;
import com.company.spring.tennis.table.dal.repositories.RoundMatchRepositroy;
import com.company.spring.tennis.table.service.types.NewMatchDto;
import com.company.spring.tennis.table.service.utilities.PropertiesConfiguration;
import com.company.spring.tennis.table.service.utilities.RejectionReason;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;
import com.company.spring.tennis.table.service.validation.interfaces.RequestValidation;

@Component("IsValidMatch")
public class IsValidMatch implements RequestValidation {

	@Autowired
	private RoundMatchRepositroy matchRepository;

	@Autowired
	private PropertiesConfiguration propertiesConfiguration;

	@Autowired
	private ParticipantRepositroy participentRepositroy;

	@Override
	public void validate(Object request) throws ValidationException {

		try {

			NewMatchDto newMatchDto = (NewMatchDto) request;

			if (!participentRepositroy.existsByUserName(newMatchDto.getFirstPlayerUsername())) {

				throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
						ResponseStatusCodesAndMessages.FIRST_PLAYER_NOT_EXIST, newMatchDto.getFirstPlayerUsername()));
			}

			if (!participentRepositroy.existsByUserName(newMatchDto.getFirstPlayerUsername())) {

				throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
						ResponseStatusCodesAndMessages.SECOND_PLAYER_NOT_EXIST, newMatchDto.getFirstPlayerUsername()));
			}

			List<Date> matchesDate = matchRepository.getLaunchDates();
			int matchesCount = 0;
			for (Date matchDate : matchesDate) {
				LocalDate localDate1 = matchDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate localDate2 = newMatchDto.getLaunchTime().toInstant().atZone(ZoneId.systemDefault())
						.toLocalDate();

				if (localDate1.isEqual(localDate2) && ++matchesCount >= propertiesConfiguration.getMaxMatchesPerDay()) {
					throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
							ResponseStatusCodesAndMessages.DATE_CONFLICT));
				}
			}

		} catch (RuntimeException e) {
			throw new ValidationException(new RejectionReason(ResponseStatusCodesAndMessages.BAD_REQUEST_CODE,
					ResponseStatusCodesAndMessages.VALIDATION_ERROR));
		}

	}

}
