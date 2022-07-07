package com.serasa.erestrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.Origem;
import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.repository.OrigemRepository;
import com.serasa.erestrito.repository.TipoProdutoRepository;

@Service
public class OrigemService {
	@Autowired
	private OrigemRepository repository;
	
	public List<Origem> listarTodos(){
		return repository.findAll();
	}
	
	public Optional<Origem> listarPorId(Long id) {
		return repository.findById(id);	
	}
	
	public Origem salvar(Origem origem) {
		return repository.save(origem);
	}
	
	public void apagar(Origem origem) {
		repository.delete(origem);
	}

}
