package com.nisum.pruebanisum.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "PHONE")
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PHONE_ID")
	private Long id;

	@Column(name = "PHONE_NUMBER", nullable = false)
	private String number;

	@Column(name = "PHONE_CITY_CODE", nullable = false)
	private String cityCode;

	@Column(name = "PHONE_COUNTRY_CODE", nullable = false)
	private String countryCode;

	@Column(name = "USERS_USER_ID", nullable = false)
	private String idUser;

	@JoinColumn(name = "USERS_USER_ID", referencedColumnName = "USER_ID", updatable = false, insertable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UsersEntity user;
}
