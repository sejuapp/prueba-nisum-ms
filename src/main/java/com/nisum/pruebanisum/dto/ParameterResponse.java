package com.nisum.pruebanisum.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ParameterResponse implements Serializable {
    private Long id;

    private String name;

    private String value;
}
