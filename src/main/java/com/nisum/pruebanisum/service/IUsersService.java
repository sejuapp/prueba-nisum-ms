package com.nisum.pruebanisum.service;

import com.nisum.pruebanisum.dto.UserRequest;
import com.nisum.pruebanisum.exception.ErrorGeneralException;
import com.nisum.pruebanisum.jpa.entity.UsersEntity;

public interface IUsersService {
	
	public UsersEntity save(UserRequest user) throws ErrorGeneralException;

}
