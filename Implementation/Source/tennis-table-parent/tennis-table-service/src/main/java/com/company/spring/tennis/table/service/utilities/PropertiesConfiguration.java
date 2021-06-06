package com.company.spring.tennis.table.service.utilities;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/validation.properties")
@ConfigurationProperties("")
public class PropertiesConfiguration {

	private Map<String, String> requestTypes;

	@Value("${champion.month}")
	private int championDateMonth;

	@Value("${champion.day}")
	private int championDateDay;

	@Value("${champion.first.slot}")
	private int championDayFirstSlot;

	@Value("${champion.match.duration}")
	private int matchDuration;

	@Value("${champion.max.matches.day}")
	private int maxMatchesPerDay;

	@Value("${congratulation.email.title}")
	private String emailTitle;

	@Value("${congratulation.email.body}")
	private String emailBody;

	public Map<String, String> getRequestTypes() {
		return requestTypes;
	}

	public void setRequestTypes(Map<String, String> requestTypes) {
		this.requestTypes = requestTypes;
	}

	public int getChampionDateMonth() {
		return championDateMonth;
	}

	public void setChampionDateMonth(int championDateMonth) {
		this.championDateMonth = championDateMonth;
	}

	public int getChampionDateDay() {
		return championDateDay;
	}

	public void setChampionDateDay(int championDateDay) {
		this.championDateDay = championDateDay;
	}

	public int getChampionDayFirstSlot() {
		return championDayFirstSlot;
	}

	public void setChampionDayFirstSlot(int championDayFirstSlot) {
		this.championDayFirstSlot = championDayFirstSlot;
	}

	public int getMatchDuration() {
		return matchDuration;
	}

	public void setMatchDuration(int matchDuration) {
		this.matchDuration = matchDuration;
	}

	public int getMaxMatchesPerDay() {
		return maxMatchesPerDay;
	}

	public void setMaxMatchesPerDay(int maxMatchesPerDay) {
		this.maxMatchesPerDay = maxMatchesPerDay;
	}

	public String getEmailTitle() {
		return emailTitle;
	}

	public void setEmailTitle(String emailTitle) {
		this.emailTitle = emailTitle;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

}
