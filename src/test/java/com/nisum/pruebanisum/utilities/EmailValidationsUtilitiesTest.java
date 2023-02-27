package com.nisum.pruebanisum.utilities;

import com.nisum.pruebanisum.enumerator.ParameterEnum;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.ParameterEntity;
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
class EmailValidationsUtilitiesTest {
    @InjectMocks
    private EmailValidationsUtilities service;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private ParameterRepository parameterRepository;

    private static final String EXPRESION_EMAIL = "[^@ \\t\\r\\n]+@[^@ \\t\\r\\n]+\\.[^@ \\t\\r\\n]+";
    private static final String EXPRESION_ERROR = "[";
    private static final String EMAIL_VALIDO = "test@gmail.com";
    private static final String EMAIL_NO_VALIDO = "test@gmail";


    @DisplayName("validEmailTestOk:> Valida flujo correcto y que sea true")
    @Test
    void validEmailTestOkTrue() throws ErrorGeneralException {
        ParameterEntity parametro = new ParameterEntity();
        parametro.setValue(EXPRESION_EMAIL);

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));

        boolean respuesta = service.validEmail(EMAIL_VALIDO);
        Assertions.assertTrue(respuesta);
    }

    @DisplayName("validEmailTestOkFalse:> Valida flujo correcto y que sea false")
    @Test
    void validEmailTestOkFalse() throws ErrorGeneralException {
        ParameterEntity parametro = new ParameterEntity();
        parametro.setValue(EXPRESION_EMAIL);

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));

        boolean respuesta = service.validEmail(EMAIL_NO_VALIDO);
        Assertions.assertFalse(respuesta);
    }

    @DisplayName("validEmailTestErrorExpresion:> Valida mensaje de error si la expresion no compila")
    @Test
    void validEmailTestErrorExpresion() throws ErrorGeneralException {
        ParameterEntity parametro = new ParameterEntity();
        parametro.setValue(EXPRESION_ERROR);

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validEmail(EMAIL_VALIDO));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EXPRESION_INVALIDA, ex.getMessage());
    }

    @DisplayName("validEmailTestErrorNull:> Valida error generado cuando el parametro en null")
    @Test
    void validEmailTestErrorNull() {

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validEmail(null));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_OBLIGATORIO, ex.getMessage());
    }

    @DisplayName("validEmailTestErrorVacia:> Valida error generado cuando el parametro en cadena llena de espacios")
    @Test
    void validEmailTestErrorVacia() {

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validEmail("    "));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_OBLIGATORIO, ex.getMessage());
    }

    @DisplayName("validEmailTestErrorSinParametro:> Valida error generado cuando el no se puede obtener el parametro email de la bd")
    @Test
    void validEmailTestErrorSinParametro() {
        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.empty());
        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validEmail(EMAIL_VALIDO));
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_NO_EXISTE_PARAMETRO, ex.getMessage());
    }

    @DisplayName("validEmailTestErrorParametroSinValor:> Valida error generado cuando valor del parametro de bd es null")
    @Test
    void validEmailTestErrorParametroSinValor() {
        ParameterEntity parametro = new ParameterEntity();

        Mockito.when(parameterRepository.findByName(ParameterEnum.EMAIL.toString())).thenReturn(Optional.of(parametro));
        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.validEmail("test@gmail.com"));
        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_NO_EXISTE_PARAMETRO_VALOR, ex.getMessage());
    }

    @DisplayName("existsEmailTestSiExiste:> Valida que el email exista y que su respuesta es true")
    @Test
    void existsEmailTestSiExiste() {
        Mockito.when(usersRepository.countByEmail(Mockito.anyString())).thenReturn(1);
        boolean respuesta = service.existsEmail(Mockito.anyString());
        Assertions.assertTrue(respuesta);
    }

    @DisplayName("existsEmailTestNoExiste:> Valida que el email no exista y que su respuesta es false")
    @Test
    void existsEmailTestNoExiste() {
        Mockito.when(usersRepository.countByEmail(Mockito.anyString())).thenReturn(0);
        boolean respuesta = service.existsEmail(Mockito.anyString());
        Assertions.assertFalse(respuesta);
    }


}
