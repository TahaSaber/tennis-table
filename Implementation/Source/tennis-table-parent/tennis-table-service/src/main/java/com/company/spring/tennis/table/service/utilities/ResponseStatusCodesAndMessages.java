package com.company.spring.tennis.table.service.utilities;

public class ResponseStatusCodesAndMessages {

	public static final int SUCCESS_CODE = 200;
	public static final int FAILED_CODE = 500;
	public static final int DUBLICATE_RESOURCE_CODE = 409;
	public static final int BAD_REQUEST_CODE = 400;

	public static final String UNEXPECTED_RESULT = "UNEXPECTED_RESULT";
	public static final String DUBLICATE_USERNAME = "Dublicate username%";
	public static final String DUBLICATE_EMAIL = "Dublicate email";
	public static final String VALIDATION_ERROR = "Validation error";
	public static final String PARTICIPENTS_COUNT_VIOLATION = "Sorry! You can't participate right now";
	public static final String ZERO_GROUP_COUNT = "Sorry! Participate can't be divided into zero or -ve number";
	public static final String INVALID_GROUP_COUNT = "Sorry! Each group should has at least 2 players, participants count is";
	public static final String DATE_CONFLICT = "Sorry! There are three matches at the same day, we cannot add this match right now";
	public static final String FIRST_PLAYER_NOT_EXIST = "Sorry! The first player does not registered to our system";
	public static final String SECOND_PLAYER_NOT_EXIST = "Sorry! The second player does not registered to our system";
	public static final String EMAIL_NOT_EXIST = "Sorry! This email does not registered to our system";
	public static final String CHAMPION_MAIL = "The Champion is informed successfully";

}
