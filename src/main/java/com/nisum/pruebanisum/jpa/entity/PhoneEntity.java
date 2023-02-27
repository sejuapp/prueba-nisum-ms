package com.nisum.pruebanisum.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
