package com.company.spring.tennis.table.service.validation.interfaces;

import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;

public interface RequestValidation {

	void validate(Object request) throws ValidationException;
}
