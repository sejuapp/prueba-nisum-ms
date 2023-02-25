package com.nisum.pruebanisum.utilities;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.repository.UsersRepository;

@Service
public class EmailValidationsUtilities {

	@Autowired
	private UsersRepository usersRepository;

	public boolean validEmail(String email) throws ErrorGeneralException {

		if (Objects.isNull(email)) {
			throw new ErrorGeneralException("Email es obligatorio");
		}

		String cadena = email.replace(" +", " ").trim();

		if (cadena.equals("")) {
			throw new ErrorGeneralException("Email es obligatorio");
		}

		return true;

	}

	public boolean existsEmail(String email) throws ErrorGeneralException {
		Integer cantidad = usersRepository.countByEmail(email);

		if (cantidad > 0) {
			throw new ErrorGeneralException("El correo ya registrado");
		}

		return false;
	}
}
