package com.nisum.pruebanisum.utilities;

import com.nisum.pruebanisum.enumerator.ParameterEnum;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.ParameterEntity;
import com.nisum.pruebanisum.jpa.repository.ParameterRepository;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Componente de utilidad para las validaciones del email
 */
@Component
public class EmailValidationsUtilities {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    /**
     * Método que valida si el email cumple con la expresión regular
     *
     * @param email email a evaluar
     * @return true si es valido
     * @throws ErrorGeneralException Error si la expresión es invalida
     */
    public boolean validEmail(String email) throws ErrorGeneralException {

        String valor = obtenerExpresionValidarDatosRequeridos(email);

        try {
            Pattern patron = Pattern.compile(valor);
            Matcher matcher = patron.matcher(email);
            return matcher.matches();
        } catch (Exception e) {
            throw new ErrorGeneralException(Constantes.MsgError.EXPRESION_INVALIDA);
        }


    }

    /**
     * Método que obtiene la expresión regular con la cual se va a evaluar el email
     *
     * @param email email a evaluar
     * @return String con la expresion regular
     * @throws ErrorGeneralException Errores generados por la estructura del email o si no existe la expresión regular
     */
    private String obtenerExpresionValidarDatosRequeridos(String email) throws ErrorGeneralException {
        if (Objects.isNull(email)) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_OBLIGATORIO);
        }

        String cadena = email.replace(" +", " ").trim();

        if (cadena.equals("")) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_OBLIGATORIO);
        }

        Optional<ParameterEntity> optParametro = parameterRepository.findByName(ParameterEnum.EMAIL.toString());

        if (optParametro.isEmpty()) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_NO_EXISTE_PARAMETRO);
        }

        ParameterEntity parametro = optParametro.get();

        if (Objects.isNull(parametro.getValue())) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_NO_EXISTE_PARAMETRO_VALOR);
        }

        return parametro.getValue();
    }

    /**
     * Método que verifica si existe el email en la base de datos
     *
     * @param email email a verificar
     * @return true si existe false si no existe
     */
    public boolean existsEmail(String email) {
        Integer cantidad = usersRepository.countByEmail(email);
        return cantidad > 0;
    }
}
