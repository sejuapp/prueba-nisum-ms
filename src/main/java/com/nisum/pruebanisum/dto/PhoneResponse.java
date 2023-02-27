package com.nisum.pruebanisum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Dto del response telefono
 */
@Getter
@Setter
public class PhoneResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String number;
    @JsonProperty(value = "citycode")
    private String cityCode;

    @JsonProperty(value = "contrycode")
    private String countryCode;
}
