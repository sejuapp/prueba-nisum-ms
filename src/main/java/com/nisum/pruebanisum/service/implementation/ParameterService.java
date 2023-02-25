package com.nisum.pruebanisum.service.implementation;

import com.nisum.pruebanisum.dto.ParameterRequest;
import com.nisum.pruebanisum.enumerator.ParameterEnum;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.ParameterEntity;
import com.nisum.pruebanisum.jpa.repository.ParameterRepository;
import com.nisum.pruebanisum.service.IParameterService;
import com.nisum.pruebanisum.utilities.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Clase que implementa las funcionalidades de la interfaz IParameterService
 */
@Service
public class ParameterService implements IParameterService {

    @Autowired
    private ParameterRepository parameterRepository;

    @Override
    public boolean save(ParameterRequest request) throws ErrorGeneralException {

        String expresion = validData(request.getExpresion());
        Optional<ParameterEntity> optParametro = parameterRepository.findByName(ParameterEnum.EMAIL.toString());

        ParameterEntity nParametro;
        if (optParametro.isEmpty()) {
            nParametro = new ParameterEntity();
            nParametro.setName(ParameterEnum.EMAIL.toString());
            nParametro.setValue(expresion);
        } else {
            nParametro = optParametro.get();
            nParametro.setValue(expresion);
        }

        parameterRepository.save(nParametro);

        return true;
    }

    /**
     * Método que valida si la cadena con la expresión es correcta
     *
     * @param expresion expresión a guardar
     * @return String con la expresión a guardar
     * @throws ErrorGeneralException Errores si la expresión es vacia o si no compila
     */
    public String validData(String expresion) throws ErrorGeneralException {

        if (Objects.isNull(expresion) || expresion.equals("")) {
            throw new ErrorGeneralException(Constantes.MsgError.EXPRESION_VACIA);
        }

        try {
            Pattern patron = Pattern.compile(expresion);

            return patron.pattern();
        } catch (PatternSyntaxException e) {
            throw new ErrorGeneralException(Constantes.MsgError.EXPRESION_INVALIDA);
        }

    }

}
