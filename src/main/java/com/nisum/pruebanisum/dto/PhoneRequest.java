package com.nisum.pruebanisum.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhoneRequest {
	private String number;
	private String citycode;
	private String contrycode;
}
