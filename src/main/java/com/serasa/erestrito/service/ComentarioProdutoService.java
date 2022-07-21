package com.serasa.erestrito.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.ComentarioProduto;
import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.repository.ComentarioProdutoRepository;

@Service
public class ComentarioProdutoService {

    @Autowired
	private ComentarioProdutoRepository repository;

	@Autowired
	private ProdutoService produtoService;
	
	public Page<ComentarioProduto> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
		//Aqui retorna todos os comentarios qualquer seja o produto
	}

	public Page<ComentarioProduto> listarTodosPorProduto(Long produtoId, Pageable paginacao) {
		Optional<Produto> produto = produtoService.listarPorId(produtoId);
		Page<ComentarioProduto> result = null;

		if (produto.isPresent()) {
			result = repository.findByProduto(produto.get(), paginacao);
		}
		return result;

		//Aqui listo todos os comentarios associado aquele produto(id)
	}

	public Optional<ComentarioProduto> listarPorId(Long id) {
		return repository.findById(id);	
	}
	
	public ComentarioProduto salvar(ComentarioProduto comentarioProduto) {
		return repository.save(comentarioProduto);
	}
	
	public void apagar(ComentarioProduto comentarioProduto) {
		repository.delete(comentarioProduto);
	}
    
}
