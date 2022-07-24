package com.serasa.erestrito.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.erestrito.domain.entity.ComentarioReceita;
import com.serasa.erestrito.domain.entity.Receita;

public interface ComentarioReceitaRepository extends JpaRepository<ComentarioReceita, Long> {
  Page<ComentarioReceita> findByReceita(Receita receita, Pageable paginacao);
}
