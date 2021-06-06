package com.company.spring.tennis.table.service.types;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ParticipantDto {

	private Integer id;

	@NotNull(message = "FristName cannot be null")
	@NotEmpty
	@Size(min = 2, message = "FristName should have at least 4 characters")
	private String fristName;

	@NotBlank(message = "LastName is mandatory")
	@NotEmpty
	@Size(min = 2, message = "LastName should have at least 4 characters")
	private String lastName;

	@NotBlank(message = "FristName is mandatory")
	@NotEmpty
	@Size(min = 2, message = "Username should have at least 2 characters")
	private String userName;

	@NotBlank(message = "Email is mandatory")
	@NotEmpty
	@Pattern(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", message = "Invalid email format")
	private String email;

	private boolean isChampion;
	private Date joinTime;
	private Date modificationTime;
	private ParticipantsGroupDto group;

	public ParticipantDto() {
	}

	public ParticipantDto(Integer id) {
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

	public void setIsChampion(Boolean isChampion) {
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

	public ParticipantsGroupDto getGroup() {
		return group;
	}

	public void setGroup(ParticipantsGroupDto group) {
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
		if (!(object instanceof ParticipantDto)) {
			return false;
		}
		ParticipantDto other = (ParticipantDto) object;
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
