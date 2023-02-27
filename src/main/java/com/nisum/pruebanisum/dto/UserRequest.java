package com.nisum.pruebanisum.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * Dto del request user
 */
@Getter
@Setter
public class UserRequest extends ModelUsersDto {
    private List<@Valid PhoneRequest> phones;
}
