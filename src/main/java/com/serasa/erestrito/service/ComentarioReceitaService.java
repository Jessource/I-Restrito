package com.serasa.erestrito.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.ComentarioReceita;
import com.serasa.erestrito.domain.entity.Receita;
import com.serasa.erestrito.repository.ComentarioReceitaRepository;

@Service
public class ComentarioReceitaService {

	@Autowired
	private ComentarioReceitaRepository repository;

	@Autowired
	private ReceitaService receitaService;

	public Page<ComentarioReceita> listarTodos(Pageable paginacao) {
		return repository.findAll(paginacao);
	}

	public Page<ComentarioReceita> listarTodosPorReceita(Long receitaId, Pageable paginacao) {
		Optional<Receita> receita = receitaService.listarPorId(receitaId);
		Page<ComentarioReceita> result = null;

		if (receita.isPresent()) {
			result = repository.findByReceita(receita.get(), paginacao);
		}
		return result;
	}

	public Optional<ComentarioReceita> listarPorId(Long id) {
		return repository.findById(id);
	}

	public ComentarioReceita salvar(ComentarioReceita comentarioReceitaComentarioReceita) {
		return repository.save(comentarioReceitaComentarioReceita);
	}

	public void apagar(ComentarioReceita comentarioReceitaComentarioReceita) {
		repository.delete(comentarioReceitaComentarioReceita);
	}

}
