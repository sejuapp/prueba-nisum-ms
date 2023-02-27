package com.nisum.pruebanisum.controller;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.service.implementation.UsersService;
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
class UsersControllerTest {

    @InjectMocks
    private UsersController controller;

    @Mock
    private UsersService iUsersService;


    @DisplayName("saveTestOk:> flujo correcto del controlador")
    @Test
    void saveTestOk() throws ErrorGeneralException {
        UserRequest request = Mockito.mock(UserRequest.class);
        UserResponse userResponse = Mockito.mock(UserResponse.class);

        Mockito.when(iUsersService.save(request)).thenReturn(userResponse);

        var respuesta = controller.save(request);
        Assertions.assertEquals(ResponseEntity.ok(userResponse), respuesta);
    }
}
