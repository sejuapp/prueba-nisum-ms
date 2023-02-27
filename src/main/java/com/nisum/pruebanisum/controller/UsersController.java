package com.nisum.pruebanisum.controller;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.dto.UserSaveResponse;
import com.nisum.pruebanisum.dto.UserUpdateRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.service.IUsersService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
public class UsersController {

    @Autowired
    private IUsersService iUsersService;

    @Operation(summary = "Servicio consulta todos los usuarios")
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        return new ResponseEntity<>(iUsersService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Servicio consulta el usuario por el id")
    @GetMapping(path = "/{idUsuario}")
    public ResponseEntity<UserResponse> getById(
            @PathVariable("idUsuario")
            String idUsuario) {
        return new ResponseEntity<>(iUsersService.getById(idUsuario), HttpStatus.OK);
    }

    @Operation(summary = "Servicio que guarda un usuario con correo como llave única")
    @PostMapping
    public ResponseEntity<UserSaveResponse> save(@Valid @RequestBody UserRequest request) throws ErrorGeneralException {
        return new ResponseEntity<>(iUsersService.save(request), HttpStatus.CREATED);
    }

    @Operation(summary = "Servicio que actualiza un usuario por medio del id")
    @PutMapping(path = "/{idUsuario}")
    public ResponseEntity<UserSaveResponse> updateById(
            @Valid @RequestBody UserUpdateRequest request,
            @PathVariable("idUsuario") String idUsuario
    ) throws ErrorGeneralException {
        return new ResponseEntity<>(iUsersService.update(idUsuario, request), HttpStatus.OK);
    }

    @Operation(summary = "Servicio que elimina es usuario del sistema por medio del id")
    @DeleteMapping(path = "/{idUsuario}")
    public ResponseEntity<UserSaveResponse> deleteById(
            @PathVariable("idUsuario") String idUsuario
    ) throws ErrorGeneralException {
        return new ResponseEntity<>(iUsersService.delete(idUsuario), HttpStatus.OK);
    }
}
