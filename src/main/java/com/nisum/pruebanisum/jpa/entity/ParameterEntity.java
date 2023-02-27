package com.nisum.pruebanisum.jpa.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "PARAMETER")
public class ParameterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PARAMETER_ID")
    private Long id;

    @Column(name = "PARAMETER_NAME", unique = true)
    private String name;

    @Column(name = "PARAMETER_VALUE")
    private String value;

}
