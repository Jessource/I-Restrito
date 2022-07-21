package com.serasa.erestrito.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.Receita;
import com.serasa.erestrito.domain.enums.Restricao;
import com.serasa.erestrito.repository.ReceitaRepository;

@Service
public class ReceitaService {

	@Autowired
	ReceitaRepository repository;

	public Page<Receita> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	public Page<Receita> listarTodos(Restricao restricao, Pageable paginacao) {
		Page<Receita> receitas = repository.findByRestricao(restricao, paginacao);
		return receitas;
	}

	public Optional<Receita> listarPorId(Long id) {
		return repository.findById(id);
	}

	public Receita salvar(Receita receita) {
		return repository.save(receita);
	}

	public void apagar(Receita receita) {
		repository.delete(receita);
	}

}
