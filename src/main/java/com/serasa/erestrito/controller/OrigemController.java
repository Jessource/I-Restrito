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

import com.serasa.erestrito.domain.entity.Origem;
import com.serasa.erestrito.domain.entity.TipoProduto;
import com.serasa.erestrito.service.OrigemService;
import com.serasa.erestrito.service.TipoProdutoService;

@RestController
@RequestMapping("/origem")
public class OrigemController {
	
	@Autowired
	OrigemService service;
	
	@GetMapping
	  public List<Origem> getAll() {
	    return service.listarTodos();
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<?> getById(@PathVariable Long id) {
	    Optional<Origem> origem = service.listarPorId(id);

	    if (origem.isPresent()) {
	      return ResponseEntity.ok(origem.get());
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @PostMapping
	  @Transactional
	  public ResponseEntity<?> salvar(@RequestBody @Valid Origem payload, UriComponentsBuilder uriBuilder) {
		  Origem origem = service.salvar(payload);
		  URI uri = uriBuilder.path("/adicao/{id}").buildAndExpand(origem.getId()).toUri();

	    return ResponseEntity.created(uri).body(origem); 
	  }

	  @PutMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid Origem form) {
	    Optional<Origem> origem = service.listarPorId(id);

	    if (origem.isPresent()) {
	    	form.setId(id);
	    	service.salvar(form);
	      
	    	return ResponseEntity.ok(service.salvar(form));
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @DeleteMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> deletar(@PathVariable Long id) {
	    Optional<Origem> origem = service.listarPorId(id);

	    if (origem.isPresent()) {
	      service.apagar(origem.get());
	      return ResponseEntity.ok(origem);
	    }
	    
	    return ResponseEntity.notFound().build();
	  }

}
