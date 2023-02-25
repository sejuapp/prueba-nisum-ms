package com.nisum.pruebanisum.service;

import com.nisum.pruebanisum.dto.ParameterRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;

/**
 * Interfaz con todos los métodos disponibles para gestionar los parametros
 */
public interface IParameterService {

    /**
     * Método que guarda la parametrica para la expresión regular del email
     *
     * @param request ParameterRequest con la expresión
     * @return boolean si se guardo
     * @throws ErrorGeneralException Errores generados al procesar la expresón regular
     */
    public boolean save(ParameterRequest request) throws ErrorGeneralException;

}
