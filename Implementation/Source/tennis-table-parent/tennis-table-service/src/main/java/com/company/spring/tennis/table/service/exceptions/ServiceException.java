package com.company.spring.tennis.table.service.exceptions;

import com.company.spring.tennis.table.service.utilities.ResponseStatusCodesAndMessages;
import com.company.spring.tennis.table.service.utilities.RejectionReason;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	private RejectionReason rejectionReason;

	public ServiceException() {
		super();
		rejectionReason = new RejectionReason(ResponseStatusCodesAndMessages.FAILED_CODE);
	}

	public ServiceException(int code) {
		rejectionReason = new RejectionReason(code);
	}

	public ServiceException(RejectionReason rejectionReason) {
		super();
		this.rejectionReason = rejectionReason;
	}

	public ServiceException(Throwable throwable) {
		super(throwable);
	}

	public ServiceException(int code, Throwable throwable) {
		super(throwable);
		rejectionReason = new RejectionReason(code, throwable.getMessage());
	}

	public RejectionReason getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(RejectionReason rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ServiceException [rejectionReason=");
		builder.append(rejectionReason);
		builder.append("]");
		return builder.toString();
	}

}
