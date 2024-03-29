package com.serasa.erestrito.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.serasa.erestrito.domain.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>  {

	List<Usuario> findByNome(String nome);

	Optional<Usuario> findByEmail(String email);
	
}
