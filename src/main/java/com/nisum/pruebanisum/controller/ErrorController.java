package com.nisum.pruebanisum.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.nisum.pruebanisum.dto.MensajeOut;
import com.nisum.pruebanisum.exception.ErrorGeneralException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class ErrorController {

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<MensajeOut> handleExceptionBadRequest(MethodArgumentNotValidException ex) {
		log.error("BAD_REQUEST: {} : {}" + ex.getLocalizedMessage(), ex);
		MensajeOut mensaje = new MensajeOut();
		mensaje.setMensaje("Error al validar los campos de entrada");
		return new ResponseEntity<MensajeOut>(mensaje, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<MensajeOut> handleExceptionInternalServer(Exception ex) {
		log.error("INTERNAL_SERVER_ERROR: {} : {}" + ex.getLocalizedMessage(), ex);
		MensajeOut mensaje = new MensajeOut();
		mensaje.setMensaje("Ha ocurrido un error en el sistema");
		return new ResponseEntity<MensajeOut>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<MensajeOut> handleExceptionNotFound(ErrorGeneralException ex) {
		log.error("ERROR_GENERAL: {} : {}" + ex.getLocalizedMessage(), ex);

		MensajeOut mensaje = new MensajeOut();
		mensaje.setMensaje(ex.getMessage());
		return new ResponseEntity<MensajeOut>(mensaje, HttpStatus.BAD_REQUEST);
	}

}
