package com.nisum.pruebanisum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequest {
	private String number;

	@JsonProperty(value = "citycode")
	private String cityCode;

	@JsonProperty(value = "contrycode")
	private String countryCode;
}
