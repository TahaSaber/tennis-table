package com.company.spring.tennis.table.service.interfaces;

import java.util.List;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.types.ParticipantDto;

public interface ParticipantService {

	ParticipantDto addParticipant(ParticipantDto participentDto) throws ServiceException;

	List<ParticipantDto> getAllParticipants() throws ServiceException;

	List<ParticipantDto> groupParticipants(Integer groupsCount) throws ServiceException;

	void submitChamion(String username) throws ServiceException;

}
