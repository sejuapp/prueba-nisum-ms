package com.nisum.pruebanisum.service;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.exception.ErrorGeneralException;

/**
 * Interfaz con todos los métodos disponibles para gestionar los usuarios
 */
public interface IUsersService {

    /**
     * Método que guarda la parametrica para la expresión regular del email
     *
     * @param user UserRequest con la información del usuario
     * @return UserResponse con la información del usuario
     * @throws ErrorGeneralException Errores generados al procesar la solicitud
     */
    public UserResponse save(UserRequest user) throws ErrorGeneralException;

}
