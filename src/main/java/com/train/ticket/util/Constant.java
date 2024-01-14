package com.train.ticket.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constant {

	public static final String EMAIL_PATTERN = "^[\\w\\.-]+@[a-zA-Z\\d\\.-]+\\.[a-zA-Z]{2,}$";
	public static final String DELET_MESSAGE = "Ticket is Cancelled Successfully : ";
	public static final String VALIDATION_ERROR = "Validation Error !  ";
	public static final String NOT_FOUND = "Not Found";
}
