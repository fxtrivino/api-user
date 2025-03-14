package com.bci.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bci.entity.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario,UUID>{

	Optional<Usuario> findByEmail(String email);
}
