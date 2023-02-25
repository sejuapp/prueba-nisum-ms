package com.nisum.pruebanisum.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MensajeErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
}
