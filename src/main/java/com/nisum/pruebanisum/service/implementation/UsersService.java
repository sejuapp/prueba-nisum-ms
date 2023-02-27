package com.nisum.pruebanisum.service.implementation;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.dto.UserSaveResponse;
import com.nisum.pruebanisum.dto.UserUpdateRequest;
import com.nisum.pruebanisum.enumerator.ParameterEnum;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import com.nisum.pruebanisum.jpa.repository.PhoneRepository;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;
import com.nisum.pruebanisum.service.IUsersService;
import com.nisum.pruebanisum.utilities.Constantes;
import com.nisum.pruebanisum.utilities.JwtTokenUtilities;
import com.nisum.pruebanisum.utilities.ObjectConverter;
import com.nisum.pruebanisum.utilities.ValidationsUtilities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class UsersService implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private ValidationsUtilities validationsUtilities;
    @Autowired
    private JwtTokenUtilities jwtTokenUtilities;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public UserSaveResponse save(UserRequest user) throws ErrorGeneralException {
        log.info("UserService: save(UserRequest user)");

        UsersEntity userSave = mapeerEntity(user);
        processValidation(userSave);

        saveData(userSave);

        return ObjectConverter.map(userSave, UserSaveResponse.class);
    }

    @Override
    public UserSaveResponse update(String id, UserUpdateRequest user) throws ErrorGeneralException {
        log.info("UserService: getId(String id)");
        Optional<UsersEntity> optUser = usersRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new ErrorGeneralException(Constantes.MsgError.USUARIO_NO_EXISTE);
        }

        UsersEntity userSave = mapeerEntityUpdate(optUser.get(), user);
        processValidation(userSave);
        saveData(userSave);

        return ObjectConverter.map(userSave, UserSaveResponse.class);

    }

    @Override
    public List<UserResponse> getAll() {
        log.info("UserService: getAll()");
        List<UsersEntity> lst = usersRepository.findAll();
        return ObjectConverter.mapAll(lst, UserResponse.class);
    }

    @Override
    public UserResponse getById(String id) {
        log.info("UserService: getId(String id)");
        Optional<UsersEntity> optUser = usersRepository.findById(id);

        if (optUser.isEmpty()) {
            return null;
        }
        return ObjectConverter.map(optUser.get(), UserResponse.class);
    }

    @Override
    public UserSaveResponse delete(String id) throws ErrorGeneralException {
        log.info("UserService: delete(String id)");
        Optional<UsersEntity> optUser = usersRepository.findById(id);

        if (optUser.isEmpty()) {
            throw new ErrorGeneralException(Constantes.MsgError.USUARIO_NO_EXISTE);
        }

        UsersEntity userSave = optUser.get();
        userSave.setActive(false);

        saveData(userSave);

        return ObjectConverter.map(userSave, UserSaveResponse.class);
    }


    /**
     * Método encargado de persistor la información de users y phone y devolver la información mapeada
     *
     * @param data UsersEntity
     */
    public void saveData(UsersEntity data) {

        usersRepository.save(data);

        if (Objects.nonNull(data.getPhones()) && !data.getPhones().isEmpty()) {
            data.getPhones().forEach(item -> item.setIdUser(data.getId()));

            phoneRepository.saveAll(data.getPhones());
        }
    }

    /**
     * Método que procesa todas las validaciones necesarias para el email
     *
     * @param user user UsersEntity que se va a actualizar
     * @return true si es valido
     * @throws ErrorGeneralException error generados al realizar las evaluaciones
     */
    public boolean processValidation(UsersEntity user) throws ErrorGeneralException {

        boolean emailValid = validationsUtilities.validarExpresion(user.getEmail(), ParameterEnum.EMAIL.toString());

        if (!emailValid) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_FORMATO);
        }

        boolean passValid = validationsUtilities.validarExpresion(user.getPassword(), ParameterEnum.PASSWORD.toString());

        if (!passValid) {
            throw new ErrorGeneralException(Constantes.MsgError.PASSWORD_FORMATO);
        }

        boolean emailExist = validationsUtilities.existsEmail(user.getEmail(), user.getId());

        if (emailExist) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_REGISTRADO);
        }

        return true;
    }

    /**
     * Método encargado de mapear el dto a clase entity para continuar con su persistencia
     *
     * @param user UserRequest
     * @return UsersEntity
     */
    public UsersEntity mapeerEntity(UserRequest user) {

        Date sysdate = new Date();

        UsersEntity entidad = ObjectConverter.map(user, UsersEntity.class);

        String token = jwtTokenUtilities.generateToken(entidad.getEmail());

        if (Objects.nonNull(entidad.getPhones())) {
            entidad.getPhones().forEach(item -> item.setActive(true));
        }

        entidad.setCreated(sysdate);
        entidad.setLastLogin(sysdate);
        entidad.setModified(sysdate);
        entidad.setToken(token);
        entidad.setActive(true);

        return entidad;
    }

    /**
     * Método para mapear un nuevo objeto a actualizar
     *
     * @param userEntity
     * @param user
     * @return
     */
    public UsersEntity mapeerEntityUpdate(UsersEntity userEntity, UserUpdateRequest user) {

        UsersEntity nUser = ObjectConverter.map(userEntity, UsersEntity.class);

        nUser.setModified(new Date());
        nUser.setName(user.getName());
        nUser.setEmail(user.getEmail());

        if (Objects.nonNull(userEntity.getPhones()) && !userEntity.getPhones().isEmpty()) {
            userEntity.getPhones().clear();
        }

        return nUser;

    }

}
