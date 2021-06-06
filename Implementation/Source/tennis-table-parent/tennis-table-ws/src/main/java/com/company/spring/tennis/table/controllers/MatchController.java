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
import org.springframework.web.bind.annotation.RestController;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.interfaces.MatchService;
import com.company.spring.tennis.table.service.types.MatchUpdateDto;
import com.company.spring.tennis.table.service.types.NewMatchDto;
import com.company.spring.tennis.table.service.types.RoundMatchDto;
import com.company.spring.tennis.table.service.utilities.Constants;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.utilities.ServiceResult;

@RestController
@RequestMapping("round-matches")
public class MatchController {

	@Autowired
	private MatchService matchService;

	@GetMapping(path = "all", produces = "application/json")
	public ResponseEntity<ServiceResult> getGeneratedFirstRoundMatches() throws ServiceException {

		List<RoundMatchDto> matchesList = matchService.getGeneratedFirstRoundMatches();

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, matchesList));
	}

	@PutMapping(path = "scores", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceResult> updateMatchScores(@RequestBody MatchUpdateDto matchUpdateDto)
			throws ServiceException {

		RoundMatchDto roundMatchDto = matchService.updateMatchScores(matchUpdateDto);

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, roundMatchDto));
	}

	@PostMapping(path = "add", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceResult> addMatch(@RequestBody NewMatchDto newMatchDto) throws ServiceException {

		RoundMatchDto roundMatchDto = matchService.addNewMatch(newMatchDto);

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, roundMatchDto));
	}


}
