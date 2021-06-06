package com.company.spring.tennis.table.service.utilities;

public class ServiceResult {

	private int code;
	private String status;
	private String message;
	private Object result;

	public ServiceResult() {
	}

	public ServiceResult(int code, String status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	public ServiceResult(int code, String status, String message, Object result) {
		this.code = code;
		this.status = status;
		this.message = message;
		this.result = result;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("ServiceResponse [code=");
//		builder.append(code);
//		builder.append(", status=");
//		builder.append(status);
//		builder.append(", message=");
//		builder.append(message);
//		builder.append(", result=");
//		builder.append(result);
//		builder.append("]");
//		return builder.toString();
//	}

}