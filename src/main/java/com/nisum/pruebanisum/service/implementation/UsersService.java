package com.nisum.pruebanisum.service.implementation;

import java.util.Date;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;
import com.nisum.pruebanisum.service.IUsersService;
import com.nisum.pruebanisum.utilities.EmailValidationsUtilities;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService implements IUsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private EmailValidationsUtilities emailValidationsUtilities;

	@Override
	public UsersEntity save(UserRequest user) throws ErrorGeneralException {
		log.info("UserService: save(UserRequest user)");

		UsersEntity userSave = mapeerEntity(user);
		validEmail(userSave.getEmail());

		return usersRepository.save(userSave);
	}

	public UsersEntity mapeerEntity(UserRequest user) {

		ModelMapper modelMapper = new ModelMapper();

		Date sysdate = new Date();

		UsersEntity entidad = modelMapper.map(user, UsersEntity.class);
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();

		entidad.setId(uuidAsString);
		entidad.setCreated(sysdate);
		entidad.setLastlogin(sysdate);
		entidad.setModified(sysdate);
		entidad.setToken(uuidAsString);
		entidad.setActive(true);

		return entidad;
	}

	public boolean validEmail(String email) throws ErrorGeneralException {

		emailValidationsUtilities.validEmail(email);
		emailValidationsUtilities.existsEmail(email);

		return true;
	}

}
