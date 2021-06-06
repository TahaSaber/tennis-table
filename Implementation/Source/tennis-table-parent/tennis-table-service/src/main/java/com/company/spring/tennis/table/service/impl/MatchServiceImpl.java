package com.company.spring.tennis.table.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.company.spring.tennis.table.dal.entities.Participant;
import com.company.spring.tennis.table.dal.entities.Round;
import com.company.spring.tennis.table.dal.entities.RoundMatch;
import com.company.spring.tennis.table.dal.repositories.ParticipantRepositroy;
import com.company.spring.tennis.table.dal.repositories.RoundMatchRepositroy;
import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.interfaces.MatchService;
import com.company.spring.tennis.table.service.types.MatchUpdateDto;
import com.company.spring.tennis.table.service.types.NewMatchDto;
import com.company.spring.tennis.table.service.types.ParticipantDto;
import com.company.spring.tennis.table.service.types.ParticipantsGroupDto;
import com.company.spring.tennis.table.service.types.RequestTypes;
import com.company.spring.tennis.table.service.types.RoundDto;
import com.company.spring.tennis.table.service.types.RoundMatchDto;
import com.company.spring.tennis.table.service.utilities.PropertiesConfiguration;
import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;
import com.company.spring.tennis.table.service.validation.impl.RequestValidatorCaller;

@Service
public class MatchServiceImpl implements MatchService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ParticipantRepositroy participantRepositroy;

	@Autowired
	private RoundMatchRepositroy matchRepository;

	@Autowired
	private RequestValidatorCaller requestValidatorCaller;

	@Autowired
	private PropertiesConfiguration propertiesConfiguration;

	@Override
	@Transactional(rollbackFor = ServiceException.class, propagation = Propagation.REQUIRED)
	public List<RoundMatchDto> getGeneratedFirstRoundMatches() throws ServiceException {
		List<RoundMatchDto> generatedMatchesList = null;

		try {

			List<RoundMatch> roundMatchesList = matchRepository.findAll();

			if (!roundMatchesList.isEmpty()) {
				log.info("getGeneratedFirstRoundMatches - roundMatchesList : {} ", roundMatchesList);
				generatedMatchesList = roundMatchesList.stream()
						.map(match -> modelMapper.map(match, RoundMatchDto.class)).collect(Collectors.toList());
				return generatedMatchesList;
			}

			RoundMatchDto matchDto = null;
			int day = propertiesConfiguration.getChampionDateDay();
			int slot = propertiesConfiguration.getChampionDayFirstSlot();
			int matchDuration = propertiesConfiguration.getMatchDuration();
			int maxMatchesPerDay = propertiesConfiguration.getMaxMatchesPerDay();
			int matchCountPerDay = 0;

			List<ParticipantDto> participentDtosList = participantRepositroy.findAll().stream()
					.map(participant -> modelMapper.map(participant, ParticipantDto.class))
					.collect(Collectors.toList());

			// group participants by group-id
			Map<ParticipantsGroupDto, List<ParticipantDto>> particioentsPerGroupMap = participentDtosList.stream()
					.collect(Collectors.groupingBy(ParticipantDto::getGroup));

			// generate first-round matches
			generatedMatchesList = new ArrayList<>();
			for (List<ParticipantDto> list : particioentsPerGroupMap.values()) {

				for (int index = 0; (index < list.size() && index + 1 < list.size()); index += 2) {

					matchDto = new RoundMatchDto();
					matchDto.setFirstPlayer(list.get(index));
					matchDto.setSecondPlayer(list.get(index + 1));
					matchDto.setRound(new RoundDto(1));
					matchDto.setLaunchTime(getMatchTime(day, slot));

					generatedMatchesList.add(matchDto);
					matchCountPerDay++;
					slot += matchDuration;
					if (matchCountPerDay >= maxMatchesPerDay) {
						day++; // second day
						matchCountPerDay = 0; // zero matches for new day
						slot = propertiesConfiguration.getChampionDayFirstSlot();
					}
				}
			}

			log.info("matches schedule : {} ", generatedMatchesList);
			List<RoundMatch> generatedMatchesEntities = generatedMatchesList.stream()
					.map(match -> modelMapper.map(match, RoundMatch.class)).collect(Collectors.toList());
			log.info("generatedMatchesEntities : {} ", generatedMatchesEntities);
			generatedMatchesList = matchRepository.saveAll(generatedMatchesEntities).stream()
					.map(match -> modelMapper.map(match, RoundMatchDto.class)).collect(Collectors.toList());

		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}

		return generatedMatchesList;
	}

	private Date getMatchTime(int day, int hour) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		cal.set(Calendar.MONTH, propertiesConfiguration.getChampionDateMonth());
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, hour);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	@Override
	public RoundMatchDto updateMatchScores(MatchUpdateDto matchUpdateDto) throws ServiceException {

		log.info("updateMatchScores : matchUpdateDto {} ", matchUpdateDto);

		RoundMatchDto matchDto = null;
		try {
			RoundMatch roundMatch = matchRepository.findById(matchUpdateDto.getMatchId()).get();
			roundMatch.setFirstPlayerScore(matchUpdateDto.getFirstScore());
			roundMatch.setSecondPlayerScore(matchUpdateDto.getSecondScore());
			matchDto = modelMapper.map(matchRepository.save(roundMatch), RoundMatchDto.class);
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}

		return matchDto;
	}

	@Override
	public RoundMatchDto addNewMatch(NewMatchDto newMatchDto) throws ServiceException {
		RoundMatchDto matchDto = null;
		try {
			requestValidatorCaller.executeValidations(newMatchDto, RequestTypes.ADD_MATCH.getValue());
			Participant firstPlayer = participantRepositroy.findByUserName(newMatchDto.getFirstPlayerUsername());
			Participant secondPlayer = participantRepositroy.findByUserName(newMatchDto.getSecondPlayerUsername());
			RoundMatch match = new RoundMatch();
			match.setFirstPlayer(firstPlayer);
			match.setSecondPlayer(secondPlayer);
			match.setLaunchTime(newMatchDto.getLaunchTime());
			if (newMatchDto.getRound() != null)
				match.setRound(new Round(newMatchDto.getRound().getId()));

			matchDto = modelMapper.map(matchRepository.save(match), RoundMatchDto.class);

		} catch (ValidationException e) {
			throw new ServiceException(e.getReason());
		} catch (RuntimeException e) {
			throw new ServiceException(e);
		}
		return matchDto;
	}

}
