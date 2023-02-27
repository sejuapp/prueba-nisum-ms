package com.nisum.pruebanisum.service;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.dto.UserSaveResponse;
import com.nisum.pruebanisum.dto.UserUpdateRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;

import java.util.List;

/**
 * Interfaz con todos los métodos disponibles para gestionar los usuarios
 */
public interface IUsersService {

    List<UserResponse> getAll();

    UserResponse getById(String id);

    /**
     * Método que guarda la parametrica para la expresión regular del email
     *
     * @param user UserRequest con la información del usuario
     * @return UserSaveResponse con la información del usuario
     * @throws ErrorGeneralException Errores generados al procesar la solicitud
     */
    UserSaveResponse save(UserRequest user) throws ErrorGeneralException;

    UserSaveResponse update(String id, UserUpdateRequest user) throws ErrorGeneralException;

    UserSaveResponse delete(String id) throws ErrorGeneralException;

}
