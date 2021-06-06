package com.company.spring.tennis.table.service.utilities;

public class RejectionReason {

	private int code;
	private String reason;
	private String parameter = "";

	public RejectionReason() {
	}

	public RejectionReason(int code) {
		this.code = code;
	}

	public RejectionReason(String reason) {
		this.reason = reason;
	}

	public RejectionReason(int code, String reason) {
		this.code = code;
		this.reason = reason;
	}

	public RejectionReason(int code, String reason, String parameter) {
		this.code = code;
		this.reason = reason;
		this.parameter = parameter;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RejectionReason [code=");
		builder.append(code);
		builder.append(", reason=");
		builder.append(reason);
		builder.append(", parameter=");
		builder.append(parameter);
		builder.append("]");
		return builder.toString();
	}

}
