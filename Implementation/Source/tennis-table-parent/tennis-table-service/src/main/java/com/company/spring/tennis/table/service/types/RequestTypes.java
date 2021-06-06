package com.company.spring.tennis.table.service.types;

public enum RequestTypes {

	ADD_PARTICIPANT("ADD_PARTICIPANT"), GROUP_PARTICIPANTS("GROUP_PARTICIPANTS"), GENERATE_MATCHES("GENERATE_MATCHES"),
	ADD_MATCH("ADD_MATCH"), SUBMIT_CHAMPION("SUBMIT_CHAMPION");

	private String value;

	private RequestTypes(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
