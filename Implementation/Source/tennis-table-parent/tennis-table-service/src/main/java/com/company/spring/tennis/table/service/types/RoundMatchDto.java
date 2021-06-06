package com.company.spring.tennis.table.service.types;

import java.util.Date;

public class RoundMatchDto {

	private Integer id;

	private Integer firstPlayerScore;

	private Integer secondPlayerScore;

	private Date launchTime;

	private ParticipantDto firstPlayer;

	private ParticipantDto secondPlayer;

	private RoundDto round;

	public RoundMatchDto() {
	}

	public RoundMatchDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFirstPlayerScore() {
		return firstPlayerScore;
	}

	public void setFirstPlayerScore(Integer firstPlayerScore) {
		this.firstPlayerScore = firstPlayerScore;
	}

	public Integer getSecondPlayerScore() {
		return secondPlayerScore;
	}

	public void setSecondPlayerScore(Integer secondPlayerScore) {
		this.secondPlayerScore = secondPlayerScore;
	}

	public ParticipantDto getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(ParticipantDto firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public ParticipantDto getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(ParticipantDto secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public Date getLaunchTime() {
		return launchTime;
	}

	public void setLaunchTime(Date launchTime) {
		this.launchTime = launchTime;
	}

	public RoundDto getRound() {
		return round;
	}

	public void setRound(RoundDto round) {
		this.round = round;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RoundMatchDto)) {
			return false;
		}
		RoundMatchDto other = (RoundMatchDto) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Match [id=");
		builder.append(id);
		builder.append(", firstPlayerScore=");
		builder.append(firstPlayerScore);
		builder.append(", secondPlayerScore=");
		builder.append(secondPlayerScore);
		builder.append(", launchTime=");
		builder.append(launchTime);
		builder.append(", firstPlayer=");
		builder.append(firstPlayer);
		builder.append(", secondPlayer=");
		builder.append(secondPlayer);
		builder.append("]");
		return builder.toString();
	}

}
