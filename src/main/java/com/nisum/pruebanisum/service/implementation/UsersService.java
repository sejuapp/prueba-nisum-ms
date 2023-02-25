package com.nisum.pruebanisum.service.implementation;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import com.nisum.pruebanisum.jpa.repository.PhoneRepository;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;
import com.nisum.pruebanisum.service.IUsersService;
import com.nisum.pruebanisum.utilities.Constantes;
import com.nisum.pruebanisum.utilities.EmailValidationsUtilities;
import com.nisum.pruebanisum.utilities.JwtTokenUtilities;
import com.nisum.pruebanisum.utilities.ObjectConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service
public class UsersService implements IUsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EmailValidationsUtilities emailValidationsUtilities;

    @Autowired
    private JwtTokenUtilities jwtTokenUtilities;

    @Override
    public UserResponse save(UserRequest user) throws ErrorGeneralException {
        log.info("UserService: save(UserRequest user)");

        UsersEntity userSave = mapeerEntity(user);
        processEmailValidation(userSave.getEmail());

        return saveData(userSave);
    }

    public UsersEntity mapeerEntity(UserRequest user) {

        Date sysdate = new Date();


        UsersEntity entidad = ObjectConverter.map(user, UsersEntity.class);
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();

        String token = jwtTokenUtilities.generateToken(entidad.getEmail());

        entidad.setId(uuidAsString);
        entidad.setCreated(sysdate);
        entidad.setLastLogin(sysdate);
        entidad.setModified(sysdate);
        entidad.setToken(token);
        entidad.setActive(true);

        return entidad;
    }

    public UserResponse saveData(UsersEntity data) {

        usersRepository.save(data);

        if (Objects.nonNull(data.getPhones()) && !data.getPhones().isEmpty()) {
            data.getPhones().forEach(item -> item.setIdUser(data.getId()));

            phoneRepository.saveAll(data.getPhones());
        }

        UserResponse userResponse = ObjectConverter.map(data, UserResponse.class);

        return userResponse;

    }

    public boolean processEmailValidation(String email) throws ErrorGeneralException {

        boolean emailValid = emailValidationsUtilities.validEmail(email);

        if (!emailValid) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_FORMATO);
        }

        boolean emailExist = emailValidationsUtilities.existsEmail(email);

        if (emailExist) {
            throw new ErrorGeneralException(Constantes.MsgError.EMAIL_REGISTRADO);
        }

        return true;
    }

}
