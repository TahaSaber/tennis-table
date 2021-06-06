package com.company.spring.tennis.table.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.interfaces.ParticipantService;
import com.company.spring.tennis.table.service.types.ParticipantDto;
import com.company.spring.tennis.table.service.utilities.Constants;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.utilities.ServiceResult;

@RestController
@RequestMapping("participent")
public class ParticipentController {

	@Autowired
	private ParticipantService participantService;

	@PostMapping(path = "add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceResult> addParticipent(@RequestBody ParticipantDto participentDto)
			throws ServiceException {

		ParticipantDto participentReponseDto = participantService.addParticipant(participentDto);

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, participentReponseDto));
	}

	@GetMapping(path = "all")
	public ResponseEntity<ServiceResult> getAllParticipants() throws ServiceException {

		List<ParticipantDto> participensList = participantService.getAllParticipants();

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, participensList));
	}

	@GetMapping(path = "group")
	public ResponseEntity<ServiceResult> groupParticipants(@RequestParam(required = true) Integer groupsCount)
			throws ServiceException {

		List<ParticipantDto> groupedParticipents = participantService.groupParticipants(groupsCount);

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, groupedParticipents));
	}

	@PutMapping(path = "champion", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceResult> submitChampion(@RequestBody ParticipantDto participentDto)
			throws ServiceException {

		participantService.submitChamion(participentDto.getEmail());

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, ResponseStatusCodesAndMessages.CHAMPION_MAIL));
	}
}
