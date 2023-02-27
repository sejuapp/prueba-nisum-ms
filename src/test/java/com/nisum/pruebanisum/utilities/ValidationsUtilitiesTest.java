package com.nisum.pruebanisum.utilities;

import com.nisum.pruebanisum.enumerator.ParameterEnum;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.ParameterEntity;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import com.nisum.pruebanisum.jpa.repository.ParameterRepository;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;
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
class ValidationsUtilitiesTest {
    @InjectMocks
    private ValidationsUtilities service;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private ParameterRepository parameterRepository;

    private static final String EXPRESION_EMAIL = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
    private static final String EXPRESION_ERROR = "[";
    private static final String EMAIL_VALIDO = "test@gmail.com";
    private static final String EMAIL_NO_VALIDO = "test@gmail";


    @DisplayName("validarExpresionOkTrue:> Valida flujo correcto y que sea true")
    @Test
    void validarExpresionOkTrue() throws ErrorGeneralException {
        ParameterEntity parametro = new ParameterEntity();
        parametro.setValue(EXPRESION_EMAIL);

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));

        boolean respuesta = service.validarExpresion(EMAIL_VALIDO, ParameterEnum.EMAIL.toString());
        Assertions.assertTrue(respuesta);
    }

    @DisplayName("validarExpresionOkFalse:> Valida flujo correcto y que sea false")
    @Test
    void validarExpresionOkFalse() throws ErrorGeneralException {
        ParameterEntity parametro = new ParameterEntity();
        parametro.setValue(EXPRESION_EMAIL);

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));

        boolean respuesta = service.validarExpresion(EMAIL_NO_VALIDO, ParameterEnum.EMAIL.toString());
        Assertions.assertFalse(respuesta);
    }

    @DisplayName("validarExpresionErrorExpresion:> Valida mensaje de error si la expresion no compila")
    @Test
    void validarExpresionErrorExpresion() throws ErrorGeneralException {
        ParameterEntity parametro = new ParameterEntity();
        parametro.setValue(EXPRESION_ERROR);

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validarExpresion(EMAIL_VALIDO, ParameterEnum.EMAIL.toString()));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EXPRESION_INVALIDA, ex.getMessage());
    }


    @DisplayName("validarExpresionTestErrorSinParametro:> Valida error generado cuando el no se puede obtener el parametro de la bd")
    @Test
    void validarExpresionTestErrorSinParametro() {
        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.empty());
        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validarExpresion(EMAIL_VALIDO, ParameterEnum.EMAIL.toString()));
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.NO_EXISTE_PARAMETRO, ex.getMessage());
    }

    @DisplayName("validarExpresionTestErrorParametroSinValor:> Valida error generado cuando valor del parametro de bd es null")
    @Test
    void validarExpresionTestErrorParametroSinValor() {
        ParameterEntity parametro = new ParameterEntity();

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));
        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validarExpresion(EMAIL_VALIDO, ParameterEnum.EMAIL.toString()));
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.NO_EXISTE_PARAMETRO_VALOR, ex.getMessage());
    }

    @DisplayName("existsEmailTestSiExiste:> Valida que el email exista y que su respuesta es true")
    @Test
    void existsEmailTestSiExiste() {

        String id = "1";
        UsersEntity user = new UsersEntity();
        user.setId("0");

        Mockito.when(usersRepository.getByEmail(Mockito.anyString())).thenReturn(Optional.of(user));
        boolean respuesta = service.existsEmail(EMAIL_VALIDO, id);
        Assertions.assertTrue(respuesta);
    }

    @DisplayName("existsEmailTestNoExiste:> Valida que el email no exista y que su respuesta es false")
    @Test
    void existsEmailTestNoExiste() {
        Mockito.when(usersRepository.getByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        boolean respuesta = service.existsEmail(EMAIL_VALIDO, "0");
        Assertions.assertFalse(respuesta);
    }


}
