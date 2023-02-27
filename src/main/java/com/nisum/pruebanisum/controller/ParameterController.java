package com.nisum.pruebanisum.controller;

import com.nisum.pruebanisum.dto.ParameterRequest;
import com.nisum.pruebanisum.dto.ParameterResponse;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.service.IParameterService;
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
@RequestMapping("parameter")
public class ParameterController {

    @Autowired
    private IParameterService iParameterService;

    @Operation(summary = "Servicio que consulta los parametros actuales")
    @GetMapping
    public ResponseEntity<List<ParameterResponse>> getAll()
            throws ErrorGeneralException {
        return new ResponseEntity<>(iParameterService.getAll(), HttpStatus.OK);
    }

    @Operation(summary = "Servicio que guarda o actualiza la expresión regular del password")
    @PutMapping
    public ResponseEntity<Boolean> save(@Valid @RequestBody ParameterRequest request)
            throws ErrorGeneralException {
        return new ResponseEntity<>(iParameterService.save(request), HttpStatus.CREATED);
    }

}
