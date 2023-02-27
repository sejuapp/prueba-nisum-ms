package com.nisum.pruebanisum.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto del request user
 */
@Getter
@Setter
public class UserRequest {
	
	@NotNull
	private String name;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	private List<PhoneRequest> phones;
}
