package com.nisum.pruebanisum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nisum.pruebanisum.utilities.Constantes;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Getter
@Setter
public class ModelPhoneDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    private String number;

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    @JsonProperty(value = "citycode")
    private String cityCode;

    @NotEmpty(message = Constantes.MsgError.NOT_EMPTY)
    @JsonProperty(value = "contrycode")
    private String countryCode;
}
