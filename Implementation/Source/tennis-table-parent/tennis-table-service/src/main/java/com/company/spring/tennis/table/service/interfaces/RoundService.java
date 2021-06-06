package com.company.spring.tennis.table.service.interfaces;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.types.RoundDto;

public interface RoundService {

	void closeRound(RoundDto roundDto) throws ServiceException;

}
