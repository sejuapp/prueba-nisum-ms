package com.nisum.pruebanisum.dto;

import com.nisum.pruebanisum.utilities.Constantes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ModelUsersDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(example = "Juan Rodriguez")
    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String name;

    @Schema(example = "juan@rodriguez.org")
    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String email;

    @Schema(example = "asdEEEWE333")
    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String password;
}
