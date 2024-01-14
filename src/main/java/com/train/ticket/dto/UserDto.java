package com.train.ticket.dto;

import org.antlr.v4.runtime.misc.NotNull;

import com.train.ticket.util.Constant;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

	@NotEmpty(message = "First Name can't be empty or null")
	private String firstName;

	@NotEmpty(message = "Last Name can't be empty or null")
	private String lastName;

	@NotEmpty(message = "Email can't be empty or null")
	@Pattern(regexp = Constant.EMAIL_PATTERN, message = "Invalid Email! Please enter valid email")
	private String email;
}
