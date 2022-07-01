package com.serasa.erestrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.domain.entity.TipoRestricao;
import com.serasa.erestrito.repository.TipoProdutoRepository;
import com.serasa.erestrito.repository.TipoRestricaoRepository;

@Service
public class TipoProdutoService {
	
	@Autowired
	private TipoProdutoRepository repository;
	
	public List<TipoProduto> listarTodos(){
		return repository.findAll();
	}
	
	public Optional<TipoProduto> listarPorId(Long id) {
		return repository.findById(id);	
	}
	
	public TipoProduto salvar(TipoProduto tipoProduto) {
		return repository.save(tipoProduto);
	}
	
	public void apagar(TipoProduto tipoProduto) {
		repository.delete(tipoProduto);
	}

}
