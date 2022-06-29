package com.serasa.erestrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.repository.TipoRestricaoRepository;
import com.serasa.erestrito.domain.entity.TipoRestricao;

@Service
public class TipoRestricaoService {
	@Autowired
	private TipoRestricaoRepository repository;
	
	public List<TipoRestricao> listarTodos(){
		return repository.findAll();
	}
	
	public Optional<TipoRestricao> listarPorId(Long id) {
		return repository.findById(id);	
	}
	
	public TipoRestricao salvar(TipoRestricao tipoRestricao) {
		return repository.save(tipoRestricao);
	}
	
	public void apagar(TipoRestricao tipoRestricao) {
		repository.delete(tipoRestricao);
	}
	
	
}
