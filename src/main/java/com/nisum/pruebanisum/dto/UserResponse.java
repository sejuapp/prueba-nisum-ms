package com.nisum.pruebanisum.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Dto del response user
 */
@Getter
@Setter
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String email;
    private Boolean active;
    private List<PhoneResponse> phones;
}
