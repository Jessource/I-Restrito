package com.serasa.erestrito.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.erestrito.domain.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
