package com.nisum.pruebanisum.dto;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
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
