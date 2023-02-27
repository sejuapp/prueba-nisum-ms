package com.nisum.pruebanisum.dto;

import com.nisum.pruebanisum.utilities.Constantes;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Dto del request user
 */
@Getter
@Setter
public class UserRequest {

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String name;

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String email;

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String password;

    private List<@Valid PhoneRequest> phones;
}
