package com.nisum.pruebanisum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import com.nisum.pruebanisum.service.IUsersService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("user")
public class UsersController {

	@Autowired
	private IUsersService iUsersService;

	@Operation(summary = "Servicio que guarda un usuario con correo como llave única")
	@PostMapping(path = "save")
	public ResponseEntity<UsersEntity> save(@RequestBody UserRequest request) throws ErrorGeneralException {
		return new ResponseEntity<>(iUsersService.save(request), HttpStatus.OK);
	}
}
