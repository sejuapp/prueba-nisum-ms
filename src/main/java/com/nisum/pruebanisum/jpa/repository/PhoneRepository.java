package com.nisum.pruebanisum.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nisum.pruebanisum.jpa.entity.PhoneEntity;

/**
 * Repositorio que contiene todas las operaciones necesarias para gestionar la información de la
 * entidad PhoneEntity
 */
@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Long> {

}
