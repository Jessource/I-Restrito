package com.serasa.erestrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.Marca;
import com.serasa.erestrito.domain.entity.TipoRestricao;
import com.serasa.erestrito.repository.MarcaRepository;
import com.serasa.erestrito.repository.TipoRestricaoRepository;

@Service
public class MarcaService {
	@Autowired
	private MarcaRepository repository;
	
	public List<Marca> listarTodos(){
		return repository.findAll();
	}
	
	public Optional<Marca> listarPorId(Long id) {
		return repository.findById(id);	
	}
	
	public Marca salvar(Marca marca) {
		return repository.save(marca);
	}
	
	public void apagar(Marca marca) {
		repository.delete(marca);
	}

}
