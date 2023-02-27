package com.nisum.pruebanisum.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * OBSERVACION: El 'name = "USER"' esta reservado, se utiliza 'name = "USERS"'
 */

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UsersEntity {

    @Id
    @Column(name = "USER_ID", nullable = false)
    private String id;

    @Column(name = "USER_NAME", nullable = false)
    private String name;

    @Column(name = "USER_EMAIL", unique = true, nullable = false)
    private String email;

    @Column(name = "USER_PASSWORD", nullable = false)
    private String password;

    @Column(name = "USER_CREATED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column(name = "USER_MODIFIED", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date modified;

    @Column(name = "USER_LAST_LOGIN", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;

    @Column(name = "USER_TOKEN", nullable = false)
    private String token;

    @Column(name = "USER_ACTIVE", nullable = false)
    private Boolean active;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PhoneEntity> phones;

    public UsersEntity() {
        this.id = UUID.randomUUID().toString();
    }
}
