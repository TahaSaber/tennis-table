package com.company.spring.tennis.table.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.spring.tennis.table.service.exceptions.ServiceException;
import com.company.spring.tennis.table.service.interfaces.RoundService;
import com.company.spring.tennis.table.service.types.RoundDto;
import com.company.spring.tennis.table.service.utilities.Constants;
import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.utilities.ServiceResult;

@RestController
@RequestMapping("round")
public class RoundController {

	@Autowired
	private RoundService roundService;

	@PutMapping(path = "close", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ServiceResult> closeRound(@RequestBody RoundDto roundDto) throws ServiceException {

		roundService.closeRound(roundDto);

		return ResponseEntity.status(HttpStatus.OK).body(new ServiceResult(ResponseStatusCodesAndMessages.SUCCESS_CODE,
				Constants.SUCCESS, Constants.SUCCESS, "Round closed successfully"));
	}

}
