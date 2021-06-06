package com.company.spring.tennis.table.service.types;

public class MatchUpdateDto {

	private Integer matchId;
	private Integer firstScore;
	private Integer secondScore;

	public MatchUpdateDto() {
	}

	public MatchUpdateDto(Integer matchId, Integer firstScore, Integer secondScore) {
		super();
		this.matchId = matchId;
		this.firstScore = firstScore;
		this.secondScore = secondScore;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public Integer getFirstScore() {
		return firstScore;
	}

	public void setFirstScore(Integer firstScore) {
		this.firstScore = firstScore;
	}

	public Integer getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(Integer secondScore) {
		this.secondScore = secondScore;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MatchUpdateDto [matchId=");
		builder.append(matchId);
		builder.append(", firstScore=");
		builder.append(firstScore);
		builder.append(", secondScore=");
		builder.append(secondScore);
		builder.append("]");
		return builder.toString();
	}

}
