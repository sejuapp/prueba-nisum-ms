package com.nisum.pruebanisum.jpa.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * OBSERVACION: El 'name = "USER"' esta reservado, se utiliza 'name = "USERS"'
 */

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UsersEntity {

	@Id
	@Column(name = "USER_ID")
	@NotNull
	private String id;

	@NotNull
	@Column(name = "NAME")
	private String name;

	@NotNull
	@Column(name = "EMAIL", unique=true)
	private String email;

	@Column(name = "PASSWORD")
	private String password;

	@NotNull
	@Column(name = "CREATED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@NotNull
	@Column(name = "MODIFIED")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	@NotNull
	@Column(name = "LAST_LOGIN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLogin;

	@NotNull
	@Column(name = "TOKEN")
	private String token;

	@NotNull
	@Column(name = "ACTIVE")
	private Boolean active;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<PhoneEntity> phones;
}
