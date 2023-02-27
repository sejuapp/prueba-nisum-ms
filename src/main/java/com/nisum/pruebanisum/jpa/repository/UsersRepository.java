package com.nisum.pruebanisum.jpa.repository;

import com.nisum.pruebanisum.jpa.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar la información de la
 * entidad UsersEntity
 */
@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, String> {

    /**
     * Consulta que obtiene es usuario por el email
     *
     * @param email email a buscar
     * @return Optional<UsersEntity>
     */
    @Query(value = """
            SELECT u FROM UsersEntity u WHERE u.email = :pEmail
            """)
    Optional<UsersEntity> getByEmail(@Param("pEmail") String email);
}
