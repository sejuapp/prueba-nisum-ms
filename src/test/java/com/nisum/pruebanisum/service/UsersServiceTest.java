package com.nisum.pruebanisum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.pruebanisum.dto.PhoneRequest;
import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.PhoneEntity;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import com.nisum.pruebanisum.jpa.repository.PhoneRepository;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;
import com.nisum.pruebanisum.service.implementation.UsersService;
import com.nisum.pruebanisum.utilities.Constantes;
import com.nisum.pruebanisum.utilities.EmailValidationsUtilities;
import com.nisum.pruebanisum.utilities.JwtTokenUtilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @InjectMocks
    private UsersService service;

    @Mock
    private EmailValidationsUtilities emailValidationsUtilities;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private PhoneRepository phoneRepository;

    @Mock
    private JwtTokenUtilities jwtTokenUtilities;

    private ObjectMapper om;

    private static final String JSON = """
            {
                "name": "Juan Rodriguez",
                "email": "juan@rodriguez.org",
                "password": "hunter2",
                "phones": [
                    {
                    "number": "1234567",
                    "citycode": "1",
                    "contrycode": "57"
                    }
                ]
            }
            """;

    @BeforeEach
    public void setup() throws ErrorGeneralException {
        om = new ObjectMapper();
    }

    @DisplayName("saveTest:> Valida proceso del guardado")
    @Test
    void saveTest() throws JsonProcessingException, ErrorGeneralException {
        UserRequest input = om.readValue(JSON, UserRequest.class);

        Mockito.when(jwtTokenUtilities.generateToken(Mockito.anyString())).thenReturn("token");
        Mockito.when(emailValidationsUtilities.validEmail(input.getEmail())).thenReturn(true);
        Mockito.when(emailValidationsUtilities.existsEmail(input.getEmail())).thenReturn(false);

        UserResponse response = service.save(input);

        Assertions.assertNotNull(response.getId());
        Mockito.verify(usersRepository).save(Mockito.any());
        Mockito.verify(phoneRepository).saveAll(Mockito.any());
    }


    @DisplayName("mapeerEntityTestUser:> Valida que el mapeo de la entidad UsersEntity sea correcta")
    @Test
    void mapeerEntityTestUser() throws JsonProcessingException {
        UserRequest input = om.readValue(JSON, UserRequest.class);

        Mockito.when(jwtTokenUtilities.generateToken(Mockito.anyString())).thenReturn(Mockito.anyString());

        UsersEntity entity = service.mapeerEntity(input);

        Assertions.assertEquals(input.getName(), entity.getName());
        Assertions.assertEquals(input.getEmail(), entity.getEmail());
        Assertions.assertEquals(input.getPassword(), entity.getPassword());
    }

    @DisplayName("mapeerEntityTestPhoneList:> Valida que el mapeo tenga la lista de telefonos")
    @Test
    void mapeerEntityTestPhoneList() throws JsonProcessingException {
        UserRequest input = om.readValue(JSON, UserRequest.class);

        Mockito.when(jwtTokenUtilities.generateToken(Mockito.anyString())).thenReturn(Mockito.anyString());
        UsersEntity entity = service.mapeerEntity(input);
        Assertions.assertNotNull(entity.getPhones());
    }

    @DisplayName("mapeerEntityTestPhone:> Valida que el mapeo del telefono sea correcto")
    @Test
    void mapeerEntityTestPhone() throws JsonProcessingException {
        UserRequest input = om.readValue(JSON, UserRequest.class);

        Mockito.when(jwtTokenUtilities.generateToken(Mockito.anyString())).thenReturn(Mockito.anyString());

        UsersEntity entity = service.mapeerEntity(input);

        PhoneRequest phoneInput = input.getPhones().get(0);
        PhoneEntity phoneEntity = entity.getPhones().get(0);

        Assertions.assertEquals(phoneEntity.getNumber(), phoneInput.getNumber());
        Assertions.assertEquals(phoneEntity.getCityCode(), phoneInput.getCityCode());
        Assertions.assertEquals(phoneEntity.getCountryCode(), phoneInput.getCountryCode());
    }

    @DisplayName("saveDataTest:> Valida se llamen los save necesarios")
    @Test
    void saveDataTest() {
        UsersEntity user = Mockito.mock(UsersEntity.class);
        List<PhoneEntity> lst = Mockito.mock(List.class);

        Mockito.when(user.getPhones()).thenReturn(lst);

        service.saveData(user);

        Mockito.verify(usersRepository).save(user);
        Mockito.verify(phoneRepository).saveAll(lst);
    }

    @DisplayName("processEmailValidationTestOk:> Valida el proceso correcto")
    @Test
    void processEmailValidationTestOk() throws ErrorGeneralException {

        Mockito.when(emailValidationsUtilities.validEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(emailValidationsUtilities.existsEmail(Mockito.anyString())).thenReturn(false);

        boolean respuesta = service.processEmailValidation(Mockito.anyString());
        Assertions.assertTrue(respuesta);

    }

    @DisplayName("processEmailValidationTestErrorEmailInvalido:> Valida mensaje de error cuando el email no cumple formato")
    @Test
    void processEmailValidationTestErrorEmailInvalido() throws ErrorGeneralException {

        Mockito.when(emailValidationsUtilities.validEmail(Mockito.anyString())).thenReturn(false);

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.processEmailValidation(Mockito.anyString()));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_FORMATO, ex.getMessage());
    }

    @DisplayName("processEmailValidationTestErrorEmailExiste:> Valida mensaje de error cuando el email ya esta registrado")
    @Test
    void processEmailValidationTestErrorEmailExiste() throws ErrorGeneralException {

        Mockito.when(emailValidationsUtilities.validEmail(Mockito.anyString())).thenReturn(true);
        Mockito.when(emailValidationsUtilities.existsEmail(Mockito.anyString())).thenReturn(true);

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.processEmailValidation(Mockito.anyString()));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_REGISTRADO, ex.getMessage());
    }
}
