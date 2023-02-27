package com.nisum.pruebanisum.dto;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto del request expresión regular a persistir
 */
@Getter
@Setter
public class ParameterRequest {
	@NotNull
	private String expresion;
}
