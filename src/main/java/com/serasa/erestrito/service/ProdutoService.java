package com.serasa.erestrito.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.entity.Usuario;
import com.serasa.erestrito.domain.enums.Restricao;
import com.serasa.erestrito.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	public Page<Produto> listarTodos(Pageable paginacao) {
		Page<Produto> produtos = repository.findAll(paginacao);
		return produtos;
	}

	public Page<Produto> listarProdutosDoUsuario(Usuario usuario, Pageable paginacao) {
		Page<Produto> produtos = repository.findByUsuario(usuario, paginacao);
		return produtos;
	}
	
	public Page<Produto> listarTodos(Restricao restricao, Pageable paginacao) {
		Page<Produto> produtos = repository.findByRestricao(restricao, paginacao);
		return produtos;
	}

	public Optional<Produto> listarPorId(Long id) {
		return repository.findById(id);
	}

	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	public void apagar(Produto produto) {
		repository.delete(produto);
	}

}
