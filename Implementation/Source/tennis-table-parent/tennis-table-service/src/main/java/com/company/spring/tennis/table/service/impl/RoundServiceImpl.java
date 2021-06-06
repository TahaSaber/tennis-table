package com.company.spring.tennis.table.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.spring.tennis.table.dal.entities.Round;
import com.company.spring.tennis.table.dal.entities.RoundState;
import com.company.spring.tennis.table.dal.repositories.RoundRepositroy;
import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.interfaces.RoundService;
import com.company.spring.tennis.table.service.types.RoundDto;

@Service
public class RoundServiceImpl implements RoundService {

	@Autowired
	private RoundRepositroy roundRepositroy;

	@Override
	public void closeRound(RoundDto roundDto) throws ServiceException {

		try {
			Round round = roundRepositroy.findById(roundDto.getId()).get();
			round.setState(new RoundState(2));
			roundRepositroy.save(round);
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}
	}

}
