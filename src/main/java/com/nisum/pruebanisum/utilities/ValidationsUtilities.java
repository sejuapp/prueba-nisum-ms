package com.nisum.pruebanisum.utilities;

import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.ParameterEntity;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
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
public class ValidationsUtilities {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ParameterRepository parameterRepository;

    /**
     * Método que obtiene la expresion regular y ejecula la validación
     *
     * @param cadenaEvaluar            cadena que se va a evaluar
     * @param nombreParametroExpresion expresión regular que se usa para validar
     * @return true si es valida
     * @throws ErrorGeneralException errores si no existe parametro
     */
    public boolean validarExpresion(String cadenaEvaluar, String nombreParametroExpresion) throws ErrorGeneralException {

        String valor = obtenerExpresion(nombreParametroExpresion);

        try {
            Pattern patron = Pattern.compile(valor);
            Matcher matcher = patron.matcher(cadenaEvaluar);
            return matcher.matches();
        } catch (Exception e) {
            throw new ErrorGeneralException(Constantes.MsgError.EXPRESION_INVALIDA);
        }
    }

    /**
     * Método que obtiene la expresion regular para ser evaluada
     *
     * @param nombreParametroExpresion identificador de la parametrica para obtener la expresión
     * @return String con la expresion regular
     * @throws ErrorGeneralException errores generados si no existe parametro
     */
    public String obtenerExpresion(String nombreParametroExpresion) throws ErrorGeneralException {

        Optional<ParameterEntity> optParametro = parameterRepository.findByName(nombreParametroExpresion);

        if (optParametro.isEmpty()) {
            throw new ErrorGeneralException(Constantes.MsgError.NO_EXISTE_PARAMETRO);
        }

        ParameterEntity parametro = optParametro.get();

        if (Objects.isNull(parametro.getValue())) {
            throw new ErrorGeneralException(Constantes.MsgError.NO_EXISTE_PARAMETRO_VALOR);
        }

        return parametro.getValue();
    }


    /**
     * Método que verifica si existe el email en la base de datos
     *
     * @param email email a verificar
     * @return true si existe false si no existe
     */
    public boolean existsEmail(String email, String idUsuario) {
        Optional<UsersEntity> opt = usersRepository.getByEmail(email);

        if (opt.isEmpty()) {
            return false;
        }
        UsersEntity usuario = opt.get();

        if (Objects.equals(usuario.getId(), idUsuario)) {
            return false;
        }


        return true;
    }
}
