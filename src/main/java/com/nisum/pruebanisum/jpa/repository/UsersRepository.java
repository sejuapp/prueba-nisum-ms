package com.nisum.pruebanisum.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nisum.pruebanisum.jpa.entity.UsersEntity;

import feign.Param;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar la información de la
 * entidad UsersEntity
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

	/**
	 * Consulta que obtiene la cantidad de correos que existen
	 *
	 * @param email email a buscar
	 * @return 1 si existe 0 si no existe
	 */
	@Query(value = """
			SELECT COUNT(u) FROM UsersEntity u WHERE u.email = :email AND limit=1
			""")
	Integer countByEmail(@Param("email") String email);
}
