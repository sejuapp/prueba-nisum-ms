package com.nisum.pruebanisum.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Dto response, para todos los mensajes de error
 */
@Getter
@Setter
public class MensajeErrorResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String mensaje;
}
