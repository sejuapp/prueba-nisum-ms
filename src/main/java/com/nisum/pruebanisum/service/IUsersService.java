package com.nisum.pruebanisum.service;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.dto.UserResponse;
import com.nisum.pruebanisum.exception.ErrorGeneralException;

public interface IUsersService {

	/**
	 *
	 * @param user
	 * @return
	 * @throws ErrorGeneralException
	 */
	public UserResponse save(UserRequest user) throws ErrorGeneralException;

}
