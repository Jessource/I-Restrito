package com.serasa.erestrito.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.erestrito.domain.entity.Receita;
import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.domain.enums.Restricao;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
  Page<Receita> findByRestricao(Restricao restricao, Pageable paginacao);
  Page<Receita> findByUsuario(Usuario usuario, Pageable paginacao);
}
