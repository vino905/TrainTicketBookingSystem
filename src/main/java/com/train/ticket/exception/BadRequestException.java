package com.train.ticket.exception;

public class BadRequestException extends RuntimeException {

	private static final long serialVersionUID = 8328215113583889758L;

	public BadRequestException(String message) {
		super(message);
	}

}
