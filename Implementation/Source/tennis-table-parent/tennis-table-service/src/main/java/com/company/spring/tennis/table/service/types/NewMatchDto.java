package com.company.spring.tennis.table.service.types;

import java.util.Date;

public class NewMatchDto {

	private Date launchTime;
	private String firstPlayerUsername;
	private String secondPlayerUsername;
	private RoundDto round;

	public NewMatchDto() {
	}

	public NewMatchDto(Date launchTime, String firstPlayerUsername, String secondPlayerUsername, RoundDto round) {
		this.launchTime = launchTime;
		this.firstPlayerUsername = firstPlayerUsername;
		this.secondPlayerUsername = secondPlayerUsername;
		this.round = round;
	}

	public Date getLaunchTime() {
		return launchTime;
	}

	public void setLaunchTime(Date launchTime) {
		this.launchTime = launchTime;
	}

	public String getFirstPlayerUsername() {
		return firstPlayerUsername;
	}

	public void setFirstPlayerUsername(String firstPlayerUsername) {
		this.firstPlayerUsername = firstPlayerUsername;
	}

	public String getSecondPlayerUsername() {
		return secondPlayerUsername;
	}

	public void setSecondPlayerUsername(String secondPlayerUsername) {
		this.secondPlayerUsername = secondPlayerUsername;
	}

	public RoundDto getRound() {
		return round;
	}

	public void setRound(RoundDto round) {
		this.round = round;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewMatchDto [launchTime=");
		builder.append(launchTime);
		builder.append(", firstPlayerUsername=");
		builder.append(firstPlayerUsername);
		builder.append(", secondPlayerUsername=");
		builder.append(secondPlayerUsername);
		builder.append("]");
		return builder.toString();
	}

}
