package com.nisum.pruebanisum.dto;

import com.nisum.pruebanisum.utilities.Constantes;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ModelUsersDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String name;

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String email;

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String password;
}
