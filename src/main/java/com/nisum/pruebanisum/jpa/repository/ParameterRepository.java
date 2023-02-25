package com.nisum.pruebanisum.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nisum.pruebanisum.jpa.entity.ParameterEntity;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar la información de la
 * entidad ParameterEntity
 */
@Repository
public interface ParameterRepository extends JpaRepository<ParameterEntity, Long> {

	@Query(value = """
			SELECT p
			FROM ParameterEntity p
			WHERE
			p.name=:pName
			  """)
	Optional<ParameterEntity> findByName(String pName);
}
