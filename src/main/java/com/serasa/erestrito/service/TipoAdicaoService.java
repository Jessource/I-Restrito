package com.serasa.erestrito.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.erestrito.domain.entity.TipoAdicao;
import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.repository.TipoAdicaoRepository;
import com.serasa.erestrito.repository.TipoProdutoRepository;

@Service
public class TipoAdicaoService {
	@Autowired
	private TipoAdicaoRepository repository;
	
	public List<TipoAdicao> listarTodos(){
		return repository.findAll();
	}
	
	public Optional<TipoAdicao> listarPorId(Long id) {
		return repository.findById(id);	
	}
	
	public TipoAdicao salvar(TipoAdicao tipoAdicao) {
		return repository.save(tipoAdicao);
	}
	
	public void apagar(TipoAdicao tipoAdicao) {
		repository.delete(tipoAdicao);
	}

}
