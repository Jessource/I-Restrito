package com.serasa.erestrito.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.domain.enums.Restricao;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
  Page<Produto> findByRestricao(Restricao restricao, Pageable paginacao);
  Page<Produto> findByUsuario(Usuario usuario, Pageable paginacao);
}
