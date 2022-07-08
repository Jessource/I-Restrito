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

import com.serasa.erestrito.domain.entity.TipoRestricao;
import com.serasa.erestrito.repository.TipoRestricaoRepository;
import com.serasa.erestrito.service.TipoRestricaoService;


@RestController
@RequestMapping("/tipo-restricao")
public class TipoRestricaoController {
	@Autowired
	TipoRestricaoService service;
	
	@GetMapping
	  public List<TipoRestricao> getAll() {
	    return service.listarTodos();
	  }

	  @GetMapping("/{id}")
	  public ResponseEntity<?> getById(@PathVariable Long id) {
	    Optional<TipoRestricao> restricao = service.listarPorId(id);

	    if (restricao.isPresent()) {
	      return ResponseEntity.ok(restricao.get());
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @PostMapping
	  @Transactional
	  public ResponseEntity<?> salvar(@RequestBody @Valid TipoRestricao payload, UriComponentsBuilder uriBuilder) {
		  TipoRestricao restricao = service.salvar(payload);
		  URI uri = uriBuilder.path("/tipo-restricao/{id}").buildAndExpand(restricao.getId()).toUri();

	    return ResponseEntity.created(uri).body(restricao); 
	  }

	  @PutMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody @Valid TipoRestricao form) {
	    Optional<TipoRestricao> restricao = service.listarPorId(id);

	    if (restricao.isPresent()) {
	    	form.setId(id);
	    	service.salvar(form);
	      
	    	return ResponseEntity.ok(service.salvar(form));
	    }

	    return ResponseEntity.notFound().build();
	  }

	  @DeleteMapping("/{id}")
	  @Transactional
	  public ResponseEntity<?> deletar(@PathVariable Long id) {
	    Optional<TipoRestricao> restricao = service.listarPorId(id);

	    if (restricao.isPresent()) {
	      service.apagar(restricao.get());
	      return ResponseEntity.ok(restricao);
	    }
	    
	    return ResponseEntity.notFound().build();
	  }
	
	
	

	
	

}
