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

@Getter
@Setter
@Entity
@Table(name = "PHONE")
public class PhoneEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PHONE_ID")
	private Long id;

	@Column(name = "NUMBER")
	private String number;

	@Column(name = "CITY_CODE")
	private String citycode;

	@Column(name = "CONTRY_CODE")
	private String contrycode;

	@Column(name = "USERS_USER_ID")
	private String idUser;

	@JoinColumn(name = "USERS_USER_ID", referencedColumnName = "USER_ID", updatable = false, insertable = false)
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	private UsersEntity user;
}
