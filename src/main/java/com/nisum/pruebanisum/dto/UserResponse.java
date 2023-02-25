package com.nisum.pruebanisum.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserResponse implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private Date created;
    private Date modified;
    @JsonProperty(value = "last_login")
    private Date lastLogin;
    private String token;
    private Boolean active;

}
