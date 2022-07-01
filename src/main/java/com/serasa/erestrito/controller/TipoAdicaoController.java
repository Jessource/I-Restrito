package com.serasa.erestrito.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.serasa.erestrito.domain.entity.TipoAdicao;
import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.service.TipoAdicaoService;
import com.serasa.erestrito.service.TipoProdutoService;

@RestController
@RequestMapping("/tipo-adicao")
public class TipoAdicaoController {
	
	@Autowired
	TipoAdicaoService service;
	
	@GetMapping
	  public List<TipoAdicao> getAll() {
	    return service.listarTodos();
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<?> getById(@PathVariable Long id) {
	    Optional<TipoAdicao> adicao = service.listarPorId(id);

	    if (adicao.isPresent()) {
	      return ResponseEntity.ok(adicao.get());
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @PostMapping
	  @Transactional
	  public ResponseEntity<?> salvar(@RequestBody @Valid TipoAdicao payload, UriComponentsBuilder uriBuilder) {
		  TipoAdicao adicao = service.salvar(payload);
		  URI uri = uriBuilder.path("/tipo-adicao/{id}").buildAndExpand(adicao.getId()).toUri();

	    return ResponseEntity.created(uri).body(adicao); 
	  }

	  @PutMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid TipoAdicao form) {
	    Optional<TipoAdicao> adicao = service.listarPorId(id);

	    if (adicao.isPresent()) {
	    	form.setId(id);
	    	service.salvar(form);
	      
	    	return ResponseEntity.ok(service.salvar(form));
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @DeleteMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> deletar(@PathVariable Long id) {
	    Optional<TipoAdicao> adicao = service.listarPorId(id);

	    if (adicao.isPresent()) {
	      service.apagar(adicao.get());
	      return ResponseEntity.ok(adicao);
	    }
	    
	    return ResponseEntity.notFound().build();
	  }

}
