package com.company.spring.tennis.table.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.spring.tennis.table.dal.entities.Participant;
import com.company.spring.tennis.table.dal.entities.ParticipantsGroup;
import com.company.spring.tennis.table.dal.repositories.GroupRepositroy;
import com.company.spring.tennis.table.dal.repositories.ParticipantRepositroy;
import com.company.spring.tennis.table.dal.repositories.RoundMatchRepositroy;
import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.interfaces.EmailService;
import com.company.spring.tennis.table.service.interfaces.ParticipantService;
import com.company.spring.tennis.table.service.types.ParticipantDto;
import com.company.spring.tennis.table.service.types.RequestTypes;
import com.company.spring.tennis.table.service.utilities.PropertiesConfiguration;
import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;
import com.company.spring.tennis.table.service.validation.impl.RequestValidatorCaller;

@Service
public class ParticipantServiceImpl implements ParticipantService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ParticipantRepositroy participantRepositroy;

	@Autowired
	private GroupRepositroy groupRepositroy;

	@Autowired
	private RequestValidatorCaller requestValidatorCaller;

	@Autowired
	private RoundMatchRepositroy matchRepository;

	@Autowired
	private EmailService emailService;

	@Autowired
	private PropertiesConfiguration propertiesConfiguration;

	@Override
	public ParticipantDto addParticipant(ParticipantDto participentDto) throws ServiceException {

		ParticipantDto savedDto = null;

		try {
			requestValidatorCaller.executeValidations(participentDto, RequestTypes.ADD_PARTICIPANT.getValue());
			Date now = new Date();
			participentDto.setJoinTime(now);
			Participant participent = modelMapper.map(participentDto, Participant.class);
			participent = participantRepositroy.save(participent);
			savedDto = modelMapper.map(participent, ParticipantDto.class);

		} catch (ValidationException e) {
			throw new ServiceException(e.getReason());
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}
		return savedDto;
	}

	@Override
	public List<ParticipantDto> getAllParticipants() throws ServiceException {
		List<ParticipantDto> participentDtosList = null;

		try {

			participentDtosList = participantRepositroy.findAll().stream()
					.map(participant -> modelMapper.map(participant, ParticipantDto.class))
					.collect(Collectors.toList());

//			participentDtosList = modelMapper.map(participantRepositroy.findAll(),
//					new TypeToken<List<ParticipantDto>>() {
//					}.getType());

		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}

		return participentDtosList;
	}

	@Override
	@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
	public List<ParticipantDto> groupParticipants(Integer groupsCount) throws ServiceException {

		List<ParticipantDto> participentDtosList = null;
		try {
			requestValidatorCaller.executeValidations(groupsCount, RequestTypes.GROUP_PARTICIPANTS.getValue());

			// delete the old generated matches from old grouping
			matchRepository.deleteAll();

			// load all Participants
			List<Participant> participentsList = participantRepositroy.findAll();

			// randomize Participants
			Collections.shuffle(participentsList);

			// group them
			List<ParticipantsGroup> groupsList = groupRepositroy.findAll();

			logger.info("groupsList is {} ", groupsList);

			// Grouping algorithm to keep groups members count even number, for example if
			// participant size
			// = 6 & groupsCount = 2, then two groups will be generated {2, 4} not {3, 3}
			int playersPerGroup = participentsList.size() / groupsCount;
			if (playersPerGroup % 2 != 0)
				--playersPerGroup;

			int groupIndex = 0;
			Date now = new Date();

			for (int i = 0; i < participentsList.size(); i += playersPerGroup) {
				for (int j = i; j < (i + playersPerGroup) && j < participentsList.size(); j++) {
					participentsList.get(j).setGroup(groupsList.get(groupIndex));
					participentsList.get(j).setModificationTime(now);
				}

				if (++groupIndex >= (groupsCount - 1))
					break;

			}

			for (int i = (groupIndex * playersPerGroup); i < participentsList.size(); i++) {
				participentsList.get(i).setGroup(groupsList.get(groupIndex));
				participentsList.get(i).setModificationTime(now);
			}
			logger.info("groupParticipants {}", participentsList);

			participentDtosList = modelMapper.map(participantRepositroy.saveAll(participentsList),
					new TypeToken<List<ParticipantDto>>() {
					}.getType());

		} catch (ValidationException e) {
			throw new ServiceException(e.getReason());
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}

		return participentDtosList;
	}

	@Override
	public void submitChamion(String email) throws ServiceException {
		try {
			requestValidatorCaller.executeValidations(email, RequestTypes.SUBMIT_CHAMPION.getValue());

			Participant participant = participantRepositroy.findByEmail(email);
			participant.setIsChampion(true);
			participantRepositroy.save(participant);
			emailService.sendSimpleMessage(email, propertiesConfiguration.getEmailTitle(),
					propertiesConfiguration.getEmailBody());

		} catch (ValidationException e) {
			throw new ServiceException(e.getReason());
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}
	}

}
