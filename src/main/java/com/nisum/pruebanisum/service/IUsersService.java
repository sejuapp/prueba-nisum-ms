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

    /**
     * Método que consulta todos los usuarios
     * @return List<UserResponse>
     */
    List<UserResponse> getAll();

    /**
     *  Método que consulta un usuario por id
     * @param id id del usuario
     * @return UserResponse
     */
    UserResponse getById(String id);

    /**
     * Método que guarda la parametrica para la expresión regular del email
     *
     * @param user UserRequest con la información del usuario
     * @return UserSaveResponse con la información del usuario
     * @throws ErrorGeneralException Errores generados al procesar la solicitud
     */
    UserSaveResponse save(UserRequest user) throws ErrorGeneralException;

    /**
     * Método que actualiza el usuario por id
     * @param id id del usuario
     * @param user UserUpdateRequest
     * @return UserSaveResponse
     * @throws ErrorGeneralException errores generados en el proceso
     */
    UserSaveResponse update(String id, UserUpdateRequest user) throws ErrorGeneralException;

    /**
     * Método que elimina el usuario por id
     * @param id id del usuario
     * @return UserSaveResponse
     * @throws ErrorGeneralException  errores generados en el proceso
     */
    UserSaveResponse delete(String id) throws ErrorGeneralException;

}
