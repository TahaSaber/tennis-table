package com.company.spring.tennis.table.service.validation.exceptions;

import com.company.spring.tennis.table.service.utilities.RejectionReason;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	private RejectionReason reason;

	public ValidationException() {
		super();
	}

	public ValidationException(RejectionReason reason) {
		super();
		this.reason = reason;
	}

	public ValidationException(Throwable cause) {
		super(cause);
	}

	public RejectionReason getReason() {
		return reason;
	}

	public void setReason(RejectionReason reason) {
		this.reason = reason;
	}

}
