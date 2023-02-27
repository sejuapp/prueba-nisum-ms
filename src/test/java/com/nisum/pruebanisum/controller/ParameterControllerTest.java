package com.nisum.pruebanisum.controller;

import com.nisum.pruebanisum.dto.ParameterRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.service.implementation.ParameterService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ParameterControllerTest {

    @InjectMocks
    private ParameterController controller;

    @Mock
    private ParameterService iParameterService;


    @DisplayName("saveTestOk:> flujo correcto del controlador")
    @Test
    void saveTestOk() throws ErrorGeneralException {
        ParameterRequest request = Mockito.mock(ParameterRequest.class);

        Mockito.when(iParameterService.save(request)).thenReturn(true);

        var respuesta = controller.save(request);
        Assertions.assertEquals(ResponseEntity.ok(true), respuesta);
    }
}
