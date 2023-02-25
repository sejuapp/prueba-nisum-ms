package com.nisum.pruebanisum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nisum.pruebanisum.dto.MensajeErrorResponse;
import com.nisum.pruebanisum.exception.ErrorGeneralException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorController {

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<MensajeErrorResponse> handleExceptionBadRequest(MethodArgumentNotValidException ex) {
		log.error("BAD_REQUEST: {} : {}" + ex.getLocalizedMessage(), ex);
		MensajeErrorResponse mensaje = new MensajeErrorResponse();
		mensaje.setMensaje("Error al validar los campos de entrada");
		return new ResponseEntity<MensajeErrorResponse>(mensaje, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<MensajeErrorResponse> handleExceptionInternalServer(Exception ex) {
		log.error("INTERNAL_SERVER_ERROR: {} : {}" + ex.getLocalizedMessage(), ex);
		MensajeErrorResponse mensaje = new MensajeErrorResponse();
		mensaje.setMensaje("Ha ocurrido un error en el sistema");
		return new ResponseEntity<MensajeErrorResponse>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<MensajeErrorResponse> handleExceptionNotFound(ErrorGeneralException ex) {
		log.error("ERROR_GENERAL: {} : {}" + ex.getLocalizedMessage(), ex);

		MensajeErrorResponse mensaje = new MensajeErrorResponse();
		mensaje.setMensaje(ex.getMessage());
		return new ResponseEntity<MensajeErrorResponse>(mensaje, HttpStatus.BAD_REQUEST);
	}

}
