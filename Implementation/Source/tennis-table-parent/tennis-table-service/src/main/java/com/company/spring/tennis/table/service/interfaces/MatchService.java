package com.company.spring.tennis.table.service.interfaces;

import java.util.List;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.types.MatchUpdateDto;
import com.company.spring.tennis.table.service.types.NewMatchDto;
import com.company.spring.tennis.table.service.types.RoundMatchDto;

public interface MatchService {

	List<RoundMatchDto> getGeneratedFirstRoundMatches() throws ServiceException;

	RoundMatchDto updateMatchScores(MatchUpdateDto matchUpdateDto) throws ServiceException;
	
	RoundMatchDto addNewMatch(NewMatchDto newMatchDto) throws ServiceException;

}
