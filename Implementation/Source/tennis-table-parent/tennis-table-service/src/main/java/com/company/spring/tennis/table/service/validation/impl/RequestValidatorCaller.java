package com.company.spring.tennis.table.service.validation.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.company.spring.tennis.table.service.utilities.PropertiesConfiguration;
import com.company.spring.tennis.table.service.validation.exceptions.ValidationException;
import com.company.spring.tennis.table.service.validation.interfaces.RequestValidation;

@Component
public class RequestValidatorCaller {

	private Map<String, RequestValidation> validationsMap;

	@Autowired
	private PropertiesConfiguration propertiesConfiguration;

	@Autowired
	public RequestValidatorCaller(Map<String, RequestValidation> validationsMap) {
		this.validationsMap = validationsMap;
	}

	public void executeValidations(Object request, String requestString) throws ValidationException {
		Map<String, String> validationsPropsMap = propertiesConfiguration.getRequestTypes();
		String validations = validationsPropsMap.get(requestString);
		String[] list = validations.split(",");

		for (String name : list) {
			if (validationsMap.get(name) != null) {
				validationsMap.get(name).validate(request);
			}

		}
	}
}
