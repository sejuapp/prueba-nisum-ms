package com.nisum.pruebanisum.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.pruebanisum.dto.ParameterRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.ParameterEntity;
import com.nisum.pruebanisum.service.IParameterService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("parameter")
public class ParameterController {

	@Autowired
	private IParameterService iParameterService;

	@Operation(summary = "Servicio que guarda o actualiza la expresión regular para validar el email")
	@PostMapping(path = "save-expresion-email")
	public ResponseEntity<Boolean> save(@Valid @RequestBody ParameterRequest request)
			throws ErrorGeneralException {
		return new ResponseEntity<>(iParameterService.save(request), HttpStatus.OK);
	}

}
