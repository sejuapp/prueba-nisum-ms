package com.nisum.pruebanisum.exception;

public class ErrorGeneralException extends Exception {

	private static final long serialVersionUID = 1L;

	public ErrorGeneralException(String mensaje) {
		super(mensaje);
	}

	public ErrorGeneralException(Throwable cause) {
		super(cause);
	}

}
