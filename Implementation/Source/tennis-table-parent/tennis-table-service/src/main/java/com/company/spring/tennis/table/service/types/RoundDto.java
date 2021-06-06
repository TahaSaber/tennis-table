package com.company.spring.tennis.table.service.types;

public class RoundDto {

	private Integer id;
	private String name;
	private RoundStateDto state;

	public RoundDto() {
	}

	public RoundDto(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoundStateDto getState() {
		return state;
	}

	public void setState(RoundStateDto state) {
		this.state = state;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof RoundDto)) {
			return false;
		}
		RoundDto other = (RoundDto) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Round [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", state=");
		builder.append(state);
		builder.append("]");
		return builder.toString();
	}

}
