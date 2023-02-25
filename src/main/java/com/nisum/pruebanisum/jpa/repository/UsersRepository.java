package com.nisum.pruebanisum.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nisum.pruebanisum.jpa.entity.UsersEntity;

import feign.Param;

@Repository
public interface UsersRepository extends JpaRepository<UsersEntity, Long> {

	@Query(value = """
			SELECT COUNT(u) FROM UsersEntity u WHERE u.email = :email
			""")
	Integer countByEmail(@Param("email") String email);
}
