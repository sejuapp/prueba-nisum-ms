package com.nisum.pruebanisum.controller;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.dto.UserSaveResponse;
import com.nisum.pruebanisum.dto.UserUpdateRequest;
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
import org.springframework.http.HttpStatus;

import java.util.List;

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
        UserSaveResponse userSaveResponse = Mockito.mock(UserSaveResponse.class);

        Mockito.when(iUsersService.save(request)).thenReturn(userSaveResponse);

        var respuesta = controller.save(request);

        Assertions.assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
    }

    @DisplayName("getAllTestOk:> flujo correcto del controlador")
    @Test
    void getAllTestOk() throws ErrorGeneralException {
        Mockito.when(iUsersService.getAll()).thenReturn(List.of());
        var respuesta = controller.getAll();
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }

    @DisplayName("getByIdTestOk:> flujo correcto del controlador")
    @Test
    void getByIdTestOk() {
        String id = "0";
        UserResponse dto = Mockito.mock(UserResponse.class);
        Mockito.when(iUsersService.getById(id)).thenReturn(dto);
        var respuesta = controller.getById(id);
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }

    @DisplayName("getByIdTestOk:> flujo correcto del controlador")
    @Test
    void deleteTestOk() throws ErrorGeneralException {
        String id = "0";
        UserSaveResponse dto = Mockito.mock(UserSaveResponse.class);
        Mockito.when(iUsersService.delete(id)).thenReturn(dto);
        var respuesta = controller.deleteById(id);
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
    }

    @DisplayName("updateByIdTestOk:> flujo correcto del controlador")
    @Test
    void updateByIdTestOk() throws ErrorGeneralException {

        String id = "0";
        UserUpdateRequest request = new UserUpdateRequest();
        UserSaveResponse userSaveResponse = Mockito.mock(UserSaveResponse.class);
        Mockito.when(iUsersService.update(id, request)).thenReturn(userSaveResponse);
        var respuesta = controller.updateById(request, id);
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());

    }
}
