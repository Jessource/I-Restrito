package com.serasa.erestrito.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.serasa.erestrito.domain.entity.ComentarioProduto;
import com.serasa.erestrito.domain.entity.Produto;


public interface ComentarioProdutoRepository extends JpaRepository<ComentarioProduto, Long> {
    Page<ComentarioProduto> findByProduto(Produto produto, Pageable paginacao);
    //Além de fazer o repository do comentario produto:
    //crio uma page de comentario passando somente o produto. 
    


}


