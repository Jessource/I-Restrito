package com.serasa.erestrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.data.web.SortDefault.SortDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	public Page<Produto> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	public Optional<Produto> listarPorId(Long id) {
		return repository.findById(id);
	}

	public Produto salvar(Produto produto) {
		return repository.save(produto);
	}

	public void apagar(Produto Produto) {
		repository.delete(Produto);
	}

}
