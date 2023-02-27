package com.nisum.pruebanisum.service;

import com.nisum.pruebanisum.dto.ParameterRequest;
import com.nisum.pruebanisum.enumerator.ParameterEnum;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.ParameterEntity;
import com.nisum.pruebanisum.jpa.repository.ParameterRepository;
import com.nisum.pruebanisum.service.implementation.ParameterService;
import com.nisum.pruebanisum.utilities.Constantes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ParameterServiceTest {
    @InjectMocks
    private ParameterService service;

    @Mock
    private ParameterRepository parameterRepository;

    private static final String EXPRESION_VALIDA = "^[a-z0-9_-]{3,15}$";

    @DisplayName("saveTestOk:> Valida cuando se crea un parametro y se llama al metodo de guardado")
    @Test
    void saveTestOk() throws ErrorGeneralException {
        ParameterRequest  rParam = new ParameterRequest();
        rParam.setExpresion(EXPRESION_VALIDA);

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.empty());
        boolean respuesta = service.save(rParam);

        Assertions.assertTrue(respuesta);
        Mockito.verify(parameterRepository).save(Mockito.any());
    }

    @DisplayName("saveTestOkNuevoParametro:> Valida cuando se modifica un parametro y se llama al metodo de guardado")
    @Test
    void saveTestOkNuevoParametro() throws ErrorGeneralException {
        ParameterRequest  rParam = new ParameterRequest();
        rParam.setExpresion(EXPRESION_VALIDA);

        ParameterEntity nParametro = new ParameterEntity();

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(nParametro));
        boolean respuesta = service.save(rParam);

        Assertions.assertTrue(respuesta);
        Mockito.verify(parameterRepository).save(Mockito.any());
    }

    @DisplayName("validDataTestOk:> Valida flujo correcto")
    @Test
    void validDataTestOk() throws ErrorGeneralException {

        String respuesta = service.validData(EXPRESION_VALIDA);
        Assertions.assertEquals(EXPRESION_VALIDA, respuesta);
    }

    @DisplayName("validDataTestExpresionNull:> Valida mensaje de error si expresion es null")
    @Test
    void validDataTestExpresionNull() {
        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validData(null));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EXPRESION_VACIA, ex.getMessage());
    }

    @DisplayName("validDataTestExpresionNoCompila:> Valida mensaje de error si expresion es invalida")
    @Test
    void validDataTestExpresionNoCompila() {
        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validData("["));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EXPRESION_INVALIDA, ex.getMessage());
    }
}
