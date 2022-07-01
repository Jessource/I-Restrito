package com.serasa.erestrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.Produto;
import com.serasa.erestrito.domain.entity.TipoRestricao;
import com.serasa.erestrito.repository.ProdutoRepository;
import com.serasa.erestrito.repository.TipoRestricaoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	ProdutoRepository repository;
	
	
	public List<Produto> listarTodos(){
		return repository.findAll();
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
