package com.nisum.pruebanisum.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

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
