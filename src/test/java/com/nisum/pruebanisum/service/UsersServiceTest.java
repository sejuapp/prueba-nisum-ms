package com.nisum.pruebanisum.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.pruebanisum.dto.*;
import com.nisum.pruebanisum.enumerator.ParameterEnum;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.PhoneEntity;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import com.nisum.pruebanisum.jpa.repository.PhoneRepository;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;
import com.nisum.pruebanisum.service.implementation.UsersService;
import com.nisum.pruebanisum.utilities.Constantes;
import com.nisum.pruebanisum.utilities.JwtTokenUtilities;
import com.nisum.pruebanisum.utilities.ValidationsUtilities;
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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {

    @InjectMocks
    private UsersService service;

    @Mock
    private ValidationsUtilities validationsUtilities;

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
        Mockito.when(validationsUtilities.validarExpresion(input.getEmail(), ParameterEnum.EMAIL.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.validarExpresion(input.getPassword(), ParameterEnum.PASSWORD.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.existsEmail(Mockito.anyString(), Mockito.anyString())).thenReturn(false);

        UserSaveResponse response = service.save(input);

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

    @DisplayName("processValidationTestOk:> Valida el proceso correcto")
    @Test
    void processValidationTestOk() throws ErrorGeneralException {

        UsersEntity user = new UsersEntity();
        user.setEmail("aaa@gmail.com");
        user.setPassword("xxxxx");
        user.setId("0");

        Mockito.when(validationsUtilities.validarExpresion(user.getEmail(), ParameterEnum.EMAIL.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.validarExpresion(user.getPassword(), ParameterEnum.PASSWORD.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.existsEmail(user.getEmail(), user.getId())).thenReturn(false);

        boolean respuesta = service.processValidation(user);
        Assertions.assertTrue(respuesta);

    }

    @DisplayName("processEmailValidationTestErrorEmailInvalido:> Valida mensaje de error cuando el email no cumple formato")
    @Test
    void processValidationTestErrorEmailInvalido() throws ErrorGeneralException {
        UsersEntity user = new UsersEntity();
        user.setEmail("aaa@gmail.com");

        Mockito.when(validationsUtilities.validarExpresion(user.getEmail(), ParameterEnum.EMAIL.toString())).thenReturn(false);

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.processValidation(user));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_FORMATO, ex.getMessage());
    }

    @DisplayName("processValidationTestErrorPasswordInvalido:> Valida mensaje de error cuando el password no cumple formato")
    @Test
    void processValidationTestErrorPasswordInvalido() throws ErrorGeneralException {
        UsersEntity user = new UsersEntity();
        user.setEmail("aaa@gmail.com");
        user.setPassword("xxxxx");

        Mockito.when(validationsUtilities.validarExpresion(user.getEmail(), ParameterEnum.EMAIL.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.validarExpresion(user.getPassword(), ParameterEnum.PASSWORD.toString())).thenReturn(false);

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.processValidation(user));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.PASSWORD_FORMATO, ex.getMessage());
    }


    @DisplayName("processEmailValidationTestErrorEmailExiste:> Valida mensaje de error cuando el email ya esta registrado")
    @Test
    void processEmailValidationTestErrorEmailExiste() throws ErrorGeneralException {

        UsersEntity user = new UsersEntity();
        user.setId("0");
        user.setEmail("aaa@gmail.com");
        user.setPassword("xxxxx");

        Mockito.when(validationsUtilities.validarExpresion(user.getEmail(), ParameterEnum.EMAIL.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.validarExpresion(user.getPassword(), ParameterEnum.PASSWORD.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.existsEmail(user.getEmail(), user.getId())).thenReturn(true);

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.processValidation(user));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.EMAIL_REGISTRADO, ex.getMessage());
    }


    @Test
    void getAllTest() {
        service.getAll();
        Mockito.verify(usersRepository).findAll();
    }

    @Test
    void getIdTestNull() {
        Mockito.when(usersRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        UserResponse respuesta = service.getById(Mockito.anyString());
        Assertions.assertNull(respuesta);
    }

    @Test
    void getIdTestNotNull() {
        UsersEntity mock = Mockito.mock(UsersEntity.class);
        Mockito.when(usersRepository.findById(Mockito.anyString())).thenReturn(Optional.of(mock));
        UserResponse respuesta = service.getById(Mockito.anyString());
        Assertions.assertNotNull(respuesta);
    }


    @Test
    void deleteTestErrorNoExiste() {

        Mockito.when(usersRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.delete(Mockito.anyString()));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.USUARIO_NO_EXISTE, ex.getMessage());
    }

    @Test
    void deleteTestOk() throws ErrorGeneralException {
        boolean resultadoEsperado = false;
        UsersEntity user = new UsersEntity();
        Mockito.when(usersRepository.findById(Mockito.anyString())).thenReturn(Optional.of(user));

        UserSaveResponse respuesta = service.delete(Mockito.anyString());

        Assertions.assertEquals(resultadoEsperado, respuesta.getActive());
    }


    @Test
    void updateTestOk() throws ErrorGeneralException {
        String id = "0";

        UsersEntity usuarioBD = new UsersEntity();
        usuarioBD.setId(id);
        usuarioBD.setName("0");
        usuarioBD.setEmail("0");
        usuarioBD.setPassword("0");

        UserUpdateRequest userUpdateRequest = new UserUpdateRequest();
        userUpdateRequest.setName("0");
        userUpdateRequest.setEmail("0");
        userUpdateRequest.setPassword("0");

        Mockito.when(usersRepository.findById(id)).thenReturn(Optional.of(usuarioBD));

        Mockito.when(validationsUtilities.validarExpresion(usuarioBD.getEmail(), ParameterEnum.EMAIL.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.validarExpresion(usuarioBD.getPassword(), ParameterEnum.PASSWORD.toString())).thenReturn(true);
        Mockito.when(validationsUtilities.existsEmail(usuarioBD.getEmail(), usuarioBD.getId())).thenReturn(false);

        UserSaveResponse respuesta = service.update(id, userUpdateRequest);

        Assertions.assertEquals(usuarioBD.getId(), respuesta.getId());
    }

    @Test
    void updateTestErrorNoExisteUsuario() {
        UserUpdateRequest userUp = Mockito.mock(UserUpdateRequest.class);

        Mockito.when(usersRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        ErrorGeneralException ex =
                Assertions.assertThrows(
                        ErrorGeneralException.class,
                        () -> service.update(Mockito.anyString(), userUp));

        Assertions.assertNotNull(ex.getMessage());
        Assertions.assertEquals(Constantes.MsgError.USUARIO_NO_EXISTE, ex.getMessage());
    }

}
