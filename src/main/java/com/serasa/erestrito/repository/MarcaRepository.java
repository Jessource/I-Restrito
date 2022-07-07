package com.serasa.erestrito.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.erestrito.domain.entity.Marca;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

}
