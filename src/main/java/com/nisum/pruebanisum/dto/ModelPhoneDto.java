package com.nisum.pruebanisum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.pruebanisum.utilities.Constantes;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ModelPhoneDto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(example = "1234567")
    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String number;

    @Schema(example = "1")
    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    @JsonProperty(value = "citycode")
    private String cityCode;

    @Schema(example = "57")
    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    @JsonProperty(value = "contrycode")
    private String countryCode;
}
