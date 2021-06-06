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
@Table(name = "participant")
public class Participant implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Column(name = "frist_name")
	private String fristName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name = "user_name")
	private String userName;
	@Column(name = "email")
	private String email;
	@Column(name = "champion")
	private boolean isChampion;
	@Column(name = "join_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date joinTime;
	@Column(name = "modification_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modificationTime;
	@JoinColumn(name = "group_id", referencedColumnName = "id")
	@ManyToOne
	private ParticipantsGroup group;

	public Participant() {
	}

	public Participant(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFristName() {
		return fristName;
	}

	public void setFristName(String fristName) {
		this.fristName = fristName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean getIsChampion() {
		return isChampion;
	}

	public void setIsChampion(boolean isChampion) {
		this.isChampion = isChampion;
	}

	public Date getJoinTime() {
		return joinTime;
	}

	public void setJoinTime(Date joinTime) {
		this.joinTime = joinTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public ParticipantsGroup getGroup() {
		return group;
	}

	public void setGroup(ParticipantsGroup group) {
		this.group = group;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Participant)) {
			return false;
		}
		Participant other = (Participant) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Participent [id=");
		builder.append(id);
		builder.append(", fristName=");
		builder.append(fristName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", email=");
		builder.append(email);
		builder.append(", isChampion=");
		builder.append(isChampion);
		builder.append(", joinTime=");
		builder.append(joinTime);
		builder.append(", modificationTime=");
		builder.append(modificationTime);
		builder.append("]");
		return builder.toString();
	}

}
