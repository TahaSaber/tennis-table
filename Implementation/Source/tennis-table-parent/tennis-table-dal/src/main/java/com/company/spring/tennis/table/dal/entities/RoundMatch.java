package com.company.spring.tennis.table.dal.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author tahasaber
 */
@Entity
@Table(name = "round_match")
public class RoundMatch implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;

	@Column(name = "first_player_score")
	private Integer firstPlayerScore;

	@Column(name = "second_player_score")
	private Integer secondPlayerScore;

	@Column(name = "launch_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date launchTime;

	@JoinColumn(name = "first_player", referencedColumnName = "id")
	@ManyToOne
	private Participant firstPlayer;

	@JoinColumn(name = "second_player", referencedColumnName = "id")
	@ManyToOne
	private Participant secondPlayer;

	@JoinColumn(name = "round", referencedColumnName = "id")
	@ManyToOne
	private Round round;

	public RoundMatch() {
	}

	public RoundMatch(Integer id) {
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

	public Participant getFirstPlayer() {
		return firstPlayer;
	}

	public void setFirstPlayer(Participant firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	public Participant getSecondPlayer() {
		return secondPlayer;
	}

	public void setSecondPlayer(Participant secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	public Date getLaunchTime() {
		return launchTime;
	}

	public void setLaunchTime(Date launchTime) {
		this.launchTime = launchTime;
	}

	public Round getRound() {
		return round;
	}

	public void setRound(Round round) {
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
		if (!(object instanceof RoundMatch)) {
			return false;
		}
		RoundMatch other = (RoundMatch) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoundMatch [id=");
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
